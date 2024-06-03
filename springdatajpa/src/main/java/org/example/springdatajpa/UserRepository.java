package org.example.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByName(String name);
    User findByEmail(String email);
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByNameOrEmail(String name, String email);

    @Modifying
    @Query("UPDATE User u set u.email= :email where u.id = :id")
    int updateUserEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query("DELETE from User u where u.email = :email")
    int deleteByEmail(@Param("email")String email);


    @Query(value = "SELECT * FROM users WHERE name = ?1", nativeQuery = true)
    List<User> findByNameNative(String name);

    // 네이티브 SQL을 사용한 사용자 조회
    @Query(value = "SELECT * FROM jpa_user WHERE email LIKE %?1%", nativeQuery = true)
    List<User> findByEmailNative(String email);

    @Query(value = "SELECT COUNT(*) FROM jpa_user WHERE age > 30 AND status = 'ACTIVE'", nativeQuery = true)
    int countActiveUsersOlderThan30();

    // 네이티브 쿼리를 사용하여 특정 칼럼을 조회하는 예제 Object[]을 여러개 List로 반환한다.
    @Query(value = "SELECT name, email FROM jpa_user WHERE name LIKE %:name%", nativeQuery = true)
    List<Object[]> findUsersByNameNative(@Param("name") String name);
}
