package com.intern10.users.application.service;

import com.intern10.users.application.dto.reqeust.LoginRequestDto;
import com.intern10.users.application.dto.reqeust.SignupRequestDto;
import com.intern10.users.application.dto.response.LoginResponseDto;
import com.intern10.users.application.dto.response.UpdateRoleResponseDto;
import com.intern10.users.application.dto.response.UserInfoResponseDto;
import com.intern10.users.application.security.jwt.JwtTokenProvider;
import com.intern10.users.common.CustomException;
import com.intern10.users.common.ErrorCode;
import com.intern10.users.domain.model.Role;
import com.intern10.users.domain.model.User;
import com.intern10.users.domain.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public User signup(SignupRequestDto signupRequestDto) {

        // 1. 중복 확인
        checkDuplicateUsername(signupRequestDto.getUsername());
        checkDuplicateNickname(signupRequestDto.getNickname());

        // 2. 회원가입 데이터 생성, role = USER
        User user = User.builder()
                .username(signupRequestDto.getUsername())
                .password(signupRequestDto.getPassword())
                .nickname(signupRequestDto.getNickname())
                .role(Role.USER)
//                .role(signupRequestDto.getRole())
                .build();

        // 3. 저장
        return userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // 1. 사용자 존재 여부 확인
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(()-> new CustomException(ErrorCode.INVALID_CREDENTIALS, ErrorCode.INVALID_CREDENTIALS.getDescription()));

        // 2. 비밀번호 검증
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS, ErrorCode.INVALID_CREDENTIALS.getDescription());
        }

        // 3. access token 생성
        String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole().toString());

        return new LoginResponseDto(accessToken);
    }

    @Transactional
    public UpdateRoleResponseDto updateRole(String token, Long userId) {
        // 1. 관리자 확인
        if(!isAdminRole(token) && !jwtTokenProvider.getUserIdFromToken(token).equals(userId)){
            throw new CustomException(ErrorCode.ACCESS_DENIED, ErrorCode.ACCESS_DENIED.getDescription());
        }


        // 2. 사용자 존재 여부 확인
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorCode.INVALID_CREDENTIALS, ErrorCode.INVALID_CREDENTIALS.getDescription()));

        // 3. role = ADMIN으로 바꾸기
        user.updateRole();

        // 4. 저장
        User savedUser = userRepository.save(user);

        return new UpdateRoleResponseDto(savedUser);
    }

    // TEST 사용자 조회
    public UserInfoResponseDto userInfo(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new CustomException(ErrorCode.INVALID_CREDENTIALS, ErrorCode.INVALID_CREDENTIALS.getDescription()));
        return new UserInfoResponseDto(user);
    }


/////////////////////////////////////////////////////////////
// 함수

    private void checkDuplicateUsername(String username) {
        if(userRepository.existsByUsername(username)){
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS, ErrorCode.USER_ALREADY_EXISTS.getDescription());
        }
    }

    private void checkDuplicateNickname(String nickname) {
        if(userRepository.existsByNickname(nickname)){
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS, ErrorCode.USER_ALREADY_EXISTS.getDescription());
        }
    }


    private boolean isAdminRole(String token) {
        String jwtToken = jwtTokenProvider.extractToken(token);
        String role = jwtTokenProvider.getRoleFromToken(jwtToken);
        return "ADMIN".equals(role);
    }
}
