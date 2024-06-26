package org.example.filterexam4.service;

import org.example.filterexam4.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@Transactional
@Rollback(value = false)
class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    void registerNewuser(){
        User user = new User();
        user.setName("kang");
        user.setUsername("kang");
        user.setPassword("1234");
        user.setEmail("kang@kang.com");

        User user1 = userService.registerNewUser(user);

        assertNotNull(user1.getId());

    }

}