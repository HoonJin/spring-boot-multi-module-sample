package com.hoonjin.sample.user.domain;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {
    @NotNull(message = "cannot be null")
    @Size(min = 2)
    @Email
    private String email;

    @NotNull(message = "name cannot be null")
    @Size(min = 2)
    private String name;

    @NotNull
    @Size(min = 8, max = 24)
    private String pwd;
}
