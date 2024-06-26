package org.example.filterexam2;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void list(){
        User user = UserContext.getUser();

        //user의 유무에 따라서 비지니스가 처리 될 수 있을 거예요.

        System.out.println(user);
    }
}
