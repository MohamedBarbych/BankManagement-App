package com.gsb.dao.repository;

import com.gsb.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserIdAndUsername(Long codeUser,String nomUser);
}
