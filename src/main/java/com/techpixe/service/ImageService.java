package com.techpixe.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.techpixe.entity.Image;

public interface ImageService 
{
	public Image saveImage(MultipartFile file,String location)throws IOException;
	
	List<Image> all();
	
	Image fetchById(Long imageId);
	
	void deleteImage(Long imageId);
}
