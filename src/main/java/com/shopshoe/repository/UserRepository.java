package com.shopshoe.repository;


import com.shopshoe.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    User findByUserName(String username);
    User findByEmail(String email);
    List<User> findAll();
    @Modifying
    @Query("UPDATE User user set user.password =:password where user.id =:id")
    void changePassword(@Param(value = "id") long id, @Param(value = "password") String password);
}
