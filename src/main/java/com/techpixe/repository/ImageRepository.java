package com.techpixe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techpixe.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>
{

}
