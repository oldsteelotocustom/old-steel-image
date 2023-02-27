package com.oldsteelimage.repository;

import com.oldsteelimage.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {

    @Query("select i from Images i where i.imageName = 1")
    Optional<Images> findImageByName(String name);

    List<Images> findImageByImageName(String imageName);


}
