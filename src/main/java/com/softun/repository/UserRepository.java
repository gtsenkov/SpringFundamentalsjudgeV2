package com.softun.repository;

import com.softun.model.entity.User;
import org.apache.juli.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u.username FROM User u " +
            "ORDER BY u.username")
    List<String> findAllUsernames();

    Optional<User> findByUsername(String username);
}
