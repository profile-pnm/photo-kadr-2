package com.photoalbum.repository;

import com.photoalbum.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT * FROM USERS WHERE username = :username")
    Optional<User> findByUsername(@Param("username") String username);
}