package com.example.project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query("""
//        select u from User u where u.nickname = :username and u.password = :password
//    """)
//    User findByUsernameAndPassword(@Param("nickname") String username, @Param("password") String password);
}
