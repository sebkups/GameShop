package com.example.shop.repo;

import com.example.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByMail(String mail);
    User findByLogin(String login);

}
