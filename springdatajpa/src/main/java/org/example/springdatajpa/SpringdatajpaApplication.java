package org.example.springdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class SpringdatajpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository repository){
		return args -> {
//			//create
//			User user = new User("hong","hong@example.com");
//			repository.save(user);
//			log.info("사용자 추가 : "+ user.getName());
//			log.info("사용자 아이디 : " + user.getId());
//
//			//delete
//
//			repository.delete(user);
//			log.info("사용자 삭제 : "+ user.getName());

//			List<User> users = repository.findByName("kim");
//
//			users.forEach(user -> log.info(user.getName()+"::"+user.getEmail()));

			// 네이티브 SQL을 사용한 사용자 조회 예제
			List<User> usersByEmail = repository.findByEmailNative("example");
			usersByEmail.forEach(user -> log.info("이메일로 찾은 사용자: " + user.getEmail()));

			// 네이티브 쿼리를 사용하여 일부 칼럼을 조회하고 DTO로 반환하는 예제
			List<Object[]> usersByName = repository.findUsersByNameNative("홍");
			List<UserDto> userDtos = usersByName.stream()
					.map(result -> new UserDto((String) result[0], (String) result[1]))
					.collect(Collectors.toList());

			userDtos.forEach(userDto -> log.info("이름으로 찾은 사용자: " + userDto.getName() + ", 이메일: " + userDto.getEmail()));
		};
	}
}
