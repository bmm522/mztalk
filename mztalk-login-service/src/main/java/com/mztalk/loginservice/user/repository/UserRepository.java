package com.mztalk.loginservice.user.repository;

import com.mztalk.loginservice.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByUsername(String username);

    Optional<User> findByNickname(String nickname);

    Optional<User> findById(Long id);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
    User findByEmail(String email);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE User u set u.password = :password where u.username = :username")
//    int updatePassword(@Param(value="username") String username,@Param(value="password") String password);



//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE User u set u.mentorStatus = 'Y' where u.nickname = :nickname")
//    int updateMentorStatus(@Param(value = "nickname") String nickname);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u set u.status = 'N' where u.nickname = :nickname")
    int updateStatus(@Param(value="nickname")String nickname);

//    User findByNickname(String nickname);



}
