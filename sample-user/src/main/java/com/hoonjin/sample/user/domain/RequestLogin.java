package com.hoonjin.sample.user.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Setter(AccessLevel.NONE)
public class RequestLogin {

    @NotNull
    @Email
    @Size(min = 2)
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;
}
