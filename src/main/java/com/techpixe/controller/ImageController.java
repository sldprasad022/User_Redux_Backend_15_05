package com.techpixe.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techpixe.entity.Image;
import com.techpixe.service.ImageService;

@RestController
@RequestMapping("/api/image")
public class ImageController 
{
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/save")
	public ResponseEntity<Image> saveImage(@RequestParam MultipartFile file,@RequestParam String location) throws IOException
	{
		Image savedImage = imageService.saveImage(file,location);
		return new ResponseEntity<Image>(savedImage,HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch/{imageId}")
	public ResponseEntity<Image> ftechImage(@PathVariable Long imageId)
	{
		Image fetchById = imageService.fetchById(imageId);
		return new ResponseEntity<Image>(fetchById,HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Image>> all()
	{
		List<Image> all = imageService.all();
		return new ResponseEntity<List<Image>>(all,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{imageId}")
	public ResponseEntity<Void> deleteImage(@PathVariable Long imageId)
	{
		Image imageFound = imageService.fetchById(imageId);
		if (imageFound ==null) 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
		else
		{
			imageService.deleteImage(imageId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	
	
}
