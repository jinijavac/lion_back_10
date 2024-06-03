package org.example.springdatajpa;

import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    public static final Logger log = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Test
    void save(){
        User user = new User("kim","kimsp@exam.com");
        repository.save(user);

        System.out.println(user.getId());
    }

    @Test
    void findByName(){
        List<User> users = repository.findByName("kang");
        assertThat(users.size()).isEqualTo(4);


        List<User> users2 = repository.findByName("kim");
        assertThat(users2.size()).isEqualTo(1);

    }

    @Test
    void findByEmail(){
        User byEmail = repository.findByEmail("kang@example.com");
        System.out.println(byEmail.getName());
    }

    @Test
    void findByNameAndEmail(){
        List<User> users = repository.findByNameAndEmail("kim", "kim@exam.com");
        users.forEach(user -> System.out.println(user.getName() + "::" + user.getEmail()));
    }

    @Test
    void findByNameOrEmail(){
        List<User> users = repository.findByNameOrEmail("kim", "kim@exam.com");
        users.forEach(user -> System.out.println(user.getName() + "::" + user.getEmail()));
    }

    @Test
    void updateUserEmail(){
       int count =  repository.updateUserEmail(3L, "new_kang@exam.com");

        Optional<User> user = repository.findById(3L);

        assertThat(user.get().getEmail()).isEqualTo("new_kang@exam.com");

        Assertions.assertEquals(user.get().getEmail(),"new_kang@exam.com");
        Assertions.assertEquals(count, 1);
    }

    @Test
    void deleteByEmail(){
      int count = repository.deleteByEmail("kim@exam.com");
        System.out.println(count);
//      assertThat(count).isEqualTo(1);
    }

    @Test
    void findByNameNative() {

        List<User> usersByEmail = repository.findByEmailNative("example");
        usersByEmail.forEach(user -> log.info("이메일로 찾은 사용자: " + user.getEmail()));
    }

    @Test
    void findByEmailNative() {
    }

    @Test
    void countActiveUsersOlderThan30() {
    }

    @Test
    void findUsersByNameNative() {
        // 네이티브 쿼리를 사용하여 일부 칼럼을 조회하고 DTO로 반환하는 예제
        List<Object[]> usersByName = repository.findUsersByNameNative("c");
        List<UserDto> userDtos = usersByName.stream()
                .map(result -> new UserDto((String) result[0], (String) result[1]))
                .collect(Collectors.toList());

        userDtos.forEach(userDto -> log.info("이름으로 찾은 사용자: " + userDto.getName() + ", 이메일: " + userDto.getEmail()));

        // AssertJ를 사용하여 어설션 추가
        assertThat(userDtos).isNotEmpty();
        assertThat(userDtos).hasSize(1); // 검색 결과가 1개라면

        // 첫 번째 사용자 검증
        UserDto firstUser = userDtos.get(0);
        assertThat(firstUser.getName()).isEqualTo("carami");
        assertThat(firstUser.getEmail()).isEqualTo("carami@example.com");

        // 두 번째 사용자 검증
    }
}