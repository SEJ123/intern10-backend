package com.intern10.users.application.dto.response;

import com.intern10.users.domain.model.Role;
import com.intern10.users.domain.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TEST 사용자 조회
@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private Long userId;
    private String userName;
    private String password;
    private String nickName;
    private Role role;

    public UserInfoResponseDto(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.nickName = user.getNickname();
        this.role = user.getRole();
    }
}
