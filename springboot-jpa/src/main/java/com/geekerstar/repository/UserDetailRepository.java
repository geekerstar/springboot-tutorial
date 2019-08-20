package com.geekerstar.repository;

import com.geekerstar.model.UserDetail;
import com.geekerstar.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author geekerstar
 * date: 2019-08-20 15:30
 * description:
 */
public interface UserDetailRepository extends JpaSpecificationExecutor<UserDetail>, JpaRepository<UserDetail, Long> {

    UserDetail findByHobby(String hobby);

    @Query("select u.userName as userName,u.email as email,d.introduction as introduction,d.hobby as hobby from User u, UserDetail d where u.id = d.userId and d.hobby=?1")
    List<UserInfo> findUserInfo(String hobby);
}
