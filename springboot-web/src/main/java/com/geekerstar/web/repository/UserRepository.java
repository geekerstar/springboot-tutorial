package com.geekerstar.web.repository;

import com.geekerstar.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author geekerstar
 * date: 2019-08-20 10:56
 * description:
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameOrEmail(String username,String email);
}
