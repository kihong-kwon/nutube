package com.kkhstudy.nutube.repository;

import com.kkhstudy.nutube.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String s);
    Users findByUsername(String s);
}
