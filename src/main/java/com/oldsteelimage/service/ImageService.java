package com.oldsteelimage.service;


import com.oldsteelimage.entity.Images;
import com.oldsteelimage.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    public Images storeImage(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        var images = new Images();
        try {
            if(fileName.contains("...")){
                throw new RuntimeException();
            }
            images.setImageData(file.getBytes());
            images.setImageName(Objects.requireNonNull(file.getOriginalFilename()));
            images.setContentType(file.getContentType());
        }catch (IOException e){
            e.printStackTrace();
        }
        return imageRepository.save(images);
    }

    public List<Images> getAllImages(){
        return imageRepository.findAll();
    }

}
