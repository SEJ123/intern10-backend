package com.intern10.users.application.dto.response;

import com.intern10.users.domain.model.Role;
import com.intern10.users.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class SignupResponseDto {
    private String username;
    private String nickname;
    private List<RoleResponse> roles;

    @Getter
    @AllArgsConstructor
    private static class RoleResponse {
        private String role;
    }

    public SignupResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.roles = Collections.singletonList(new RoleResponse(user.getRole().toString()));
    }
}
