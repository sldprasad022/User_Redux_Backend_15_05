package com.techpixe.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.techpixe.entity.Image;
import com.techpixe.repository.ImageRepository;
import com.techpixe.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService
{
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public Image saveImage(MultipartFile file, String location)  throws IOException
	{
		Image image = new Image();
		image.setImageName(file.getOriginalFilename());
		image.setContentType(file.getContentType());
		image.setImageUrl(file.getBytes());
		image.setLocation(location);
		return imageRepository.save(image);
	}

	@Override
	public List<Image> all()
	{
		List<Image> savedAll= imageRepository.findAll();
		if (savedAll==null) 
		{
			throw new ResponseStatusException(HttpStatus.OK,"No Images Found");
		} 
		else
		{
			return savedAll;
		}
	}

	@Override
	public Image fetchById(Long imageId)
	{
		return imageRepository.findById(imageId).orElseThrow(()-> new ResponseStatusException(HttpStatus.OK,"No Image Found"));
	}

	@Override
	public void deleteImage(Long imageId)
	{
		imageRepository.deleteById(imageId);		
	}

}
