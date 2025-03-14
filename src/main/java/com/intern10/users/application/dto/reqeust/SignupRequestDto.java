package com.intern10.users.application.dto.reqeust;

import com.intern10.users.domain.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String nickname;
//    private Role role;
}
