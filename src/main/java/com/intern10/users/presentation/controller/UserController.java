package com.intern10.users.presentation.controller;

import com.intern10.users.application.dto.reqeust.LoginRequestDto;
import com.intern10.users.application.dto.reqeust.SignupRequestDto;
import com.intern10.users.application.dto.response.LoginResponseDto;
import com.intern10.users.application.dto.response.SignupResponseDto;
import com.intern10.users.application.dto.response.UpdateRoleResponseDto;
import com.intern10.users.application.dto.response.UserInfoResponseDto;
import com.intern10.users.application.service.UserService;
import com.intern10.users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto){
        User savedUser = userService.signup(signupRequestDto);
        SignupResponseDto signupResponseDto = new SignupResponseDto(savedUser);
        return ResponseEntity.ok(signupResponseDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    // 관리자 권한 부여
    @PatchMapping("/admin/users/{userId}/roles")
    public ResponseEntity<UpdateRoleResponseDto> updateRole(@PathVariable Long userId){
        UpdateRoleResponseDto updateRoleResponseDto = userService.updateRole(userId);
        return ResponseEntity.ok(updateRoleResponseDto);
    }

    // TEST 사용자 조회
    @GetMapping("/info/{username}")
    public ResponseEntity<UserInfoResponseDto> userInfo(@PathVariable String username){
        UserInfoResponseDto userInfoResponseDto = userService.userInfo(username);
        return ResponseEntity.ok(userInfoResponseDto);
    }

}