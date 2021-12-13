package com.hoonjin.sample.user.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class ResponseUser {

    private String email;

    private String name;

    private String userId;

    public static ResponseUser of(UserDto dto) {
        ResponseUser res = new ResponseUser();
        res.email = dto.getEmail();
        res.name = dto.getEmail();
        res.userId = dto.getUserId();
        return res;
    }
}
