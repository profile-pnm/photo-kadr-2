package com.photoalbum.repository;

import com.photoalbum.model.Photo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    @Query("SELECT * FROM photos WHERE user_id = :userId")
    List<Photo> findByUserId(@Param("userId") Long userId);
}