package com.hoonjin.sample.user.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoonjin.sample.user.entity.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter(AccessLevel.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {

    private String email;

    private String name;

    private String userId;

    private List<ResponseOrder> orders;

    public static ResponseUser of(UserDto dto) {
        ResponseUser res = new ResponseUser();
        res.email = dto.getEmail();
        res.name = dto.getEmail();
        res.userId = dto.getUserId();
        res.orders = new ArrayList<>();
        return res;
    }

    public static ResponseUser of(UserDto dto, List<ResponseOrder> orders) {
        ResponseUser res = new ResponseUser();
        res.email = dto.getEmail();
        res.name = dto.getEmail();
        res.userId = dto.getUserId();
        res.orders = orders;
        return res;
    }

    public static ResponseUser of(User user) {
        ResponseUser res = new ResponseUser();
        res.email = user.getEmail();
        res.name = user.getEmail();
        res.userId = user.getUserId();
        res.orders = new ArrayList<>();
        return res;
    }

    public static ResponseUser of(User user, List<ResponseOrder> orders) {
        ResponseUser res = new ResponseUser();
        res.email = user.getEmail();
        res.name = user.getEmail();
        res.userId = user.getUserId();
        res.orders = orders;
        return res;
    }
}
