package com.oldsteelimage.controller;

import com.oldsteelimage.dto.response.ImageResponseDto;
import com.oldsteelimage.entity.Images;
import com.oldsteelimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@Slf4j
public class ImagesController {

    private final ImageService imageService;

    public String testHai(){
        return "This is API TESTING";
    }

    @PostMapping("/upload")
            //, headers = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(@RequestParam ("file") MultipartFile file){
        var upload = imageService.storeImage(file);
        log.info("This end point just got hit");
        return new ResponseEntity<>("Upload image success", HttpStatus.OK);
    }

    @GetMapping("/get-images")
    public ResponseEntity<?> findAllImage(){
        var images = imageService.getAllImages();
        if(images.isEmpty()){
            return new ResponseEntity<>("Images not found",
                    HttpStatus.NOT_FOUND);
        }
        List<ImageResponseDto> imagesResponses = new ArrayList<>();
        for(Images image : images){
            imagesResponses.add(ImageResponseDto.dataFrom(image));
        }
        return new ResponseEntity<>(imagesResponses, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOneImage(@PathVariable("id") Long id){
        var image = imageService.getImageById(id);
        if(image == null){
            log.info("Images not found: {} ", image);
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

}
