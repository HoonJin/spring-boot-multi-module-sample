package com.hoonjin.sample.user.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 36)
    private String userId;

    @Column(nullable = false)
    @Setter
    private String encryptedPwd;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
