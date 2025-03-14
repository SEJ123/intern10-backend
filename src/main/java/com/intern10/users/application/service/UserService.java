package com.intern10.users.application.service;

import com.intern10.users.application.dto.reqeust.SignupRequestDto;
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
                .build();

        // 3. 저장
        return userRepository.save(user);
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
}
