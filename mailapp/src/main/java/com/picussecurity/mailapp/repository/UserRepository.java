package com.picussecurity.mailapp.repository;

import com.picussecurity.mailapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(Long id);
}