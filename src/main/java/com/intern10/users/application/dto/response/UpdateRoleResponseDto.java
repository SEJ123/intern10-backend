package com.intern10.users.application.dto.response;

import com.intern10.users.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateRoleResponseDto {
    private String username;
    private String nickname;
    private List<UpdateRoleResponseDto.RoleResponse> roles;

    @Getter
    @AllArgsConstructor
    private static class RoleResponse {
        private String role;
    }

    public UpdateRoleResponseDto(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.roles = Collections.singletonList(new UpdateRoleResponseDto.RoleResponse(user.getRole().toString()));
    }
}
