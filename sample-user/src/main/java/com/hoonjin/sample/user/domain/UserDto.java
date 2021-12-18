package com.hoonjin.sample.user.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;

    private String name;

    private String pwd;

    private String userId;

    private LocalDateTime createdAt;

    private String encryptedPwd;

    @Setter
    private List<ResponseOrder> orders;
}
