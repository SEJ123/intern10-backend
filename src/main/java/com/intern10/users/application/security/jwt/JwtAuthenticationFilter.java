package com.intern10.users.application.security.jwt;

import com.intern10.users.application.service.UserDetailsServiceImpl;
import com.intern10.users.common.CustomException;
import com.intern10.users.common.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 토큰 존재 여부 확인
        String token = jwtTokenProvider.getTokenFromRequest(request);

        // 토큰 만료 여부 확인
        if (token != null && !jwtTokenProvider.isTokenExpired(token)) {

            if(jwtTokenProvider.isTokenExpired(token)) {
                throw new CustomException(ErrorCode.INVALID_TOKEN, ErrorCode.INVALID_TOKEN.getDescription());
            }

            try {
                String username = jwtTokenProvider.getUsernameFromToken(token);

                // 인증 객체 생성
                Authentication authentication = createAuthentication(username, token);

                // SecurityContext에 인증 객체 설정
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authentication);
                SecurityContextHolder.setContext(context);
            } catch (Exception e) {
                throw new CustomException(ErrorCode.INVALID_TOKEN, ErrorCode.INVALID_TOKEN.getDescription());
            }

        }
        filterChain.doFilter(request, response);
    }


    private Authentication createAuthentication(String username, String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // token에서 권한
        String role = jwtTokenProvider.getRoleFromToken(token);
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

        return new UsernamePasswordAuthenticationToken(userDetails, token, authorities);
    }
}