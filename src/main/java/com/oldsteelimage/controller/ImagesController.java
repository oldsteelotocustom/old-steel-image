package com.oldsteelimage.controller;

import com.oldsteelimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@Slf4j
public class ImagesController {

    private final ImageService imageService;
    @GetMapping("/")
    public String testHai(){
        return "This is API TESTING";
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile file){
        var upload = imageService.storeImage(file);
        return new ResponseEntity<>("Upload image success", HttpStatus.OK);
    }

    @GetMapping("/get-images")
    public ResponseEntity<?> findAllImage(){
        var images = imageService.getAllImages();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }

}
