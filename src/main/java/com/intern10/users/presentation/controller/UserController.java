package com.intern10.users.presentation.controller;

import com.intern10.users.application.dto.reqeust.SignupRequestDto;
import com.intern10.users.application.dto.response.SignupResponseDto;
import com.intern10.users.application.service.UserService;
import com.intern10.users.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
