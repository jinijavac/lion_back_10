package com.example.jpa;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class UserMain {
//    private static final Logger log = LoggerFactory.getLogger(UserMain.class);
    public static void main(String[] args) {


        UserDAO userDAO = new UserDAO();


//        //findUser()
//        log.info("==============findUser()===========");
//        User findUser = userDAO.findUser(1L);
//        log.info("Found user : {}",findUser.getName());
//
//
//        User findUser2 =   userDAO.findUser(1L);
//
//        if(findUser == findUser2)
//            log.info("findUser == findUser2 ");
//        else
//            log.info("findUser != findUser2 ");

        User user = new User();
        user.setId(2L);
//        user.setName("carami");
//        user.setEmail("carami@exam.com");

        userDAO.deleteUser(user);

//        userDAO.updateUser(user);

        //update!!




//
//        User caramiUser = new User("urstory","urstory@exam.com");
//
//        userDAO.createUser(caramiUser);
//
//        log.info("Create user : "+ caramiUser.getName());
//        log.info("user email : {}",caramiUser.getEmail() );


    }
}
