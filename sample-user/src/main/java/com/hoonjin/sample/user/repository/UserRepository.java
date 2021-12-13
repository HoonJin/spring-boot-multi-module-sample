package com.hoonjin.sample.user.repository;

import com.hoonjin.sample.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
