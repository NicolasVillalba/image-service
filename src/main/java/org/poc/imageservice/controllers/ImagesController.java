package org.poc.imageservice.controllers;

import org.poc.imageservice.domain.ImageAsset;
import org.poc.imageservice.dtos.PathResponseDTO;
import org.poc.imageservice.repositories.ImagesRepository;
import org.poc.imageservice.services.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImagesController {

    private ImagesRepository images;

    private StorageService storageService;

    public ImagesController(ImagesRepository repository, StorageService storageService){
        this.images = repository;
        this.storageService = storageService;
    }

    @GetMapping("/")
    public List<ImageAsset> getAll(){
        return images.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<PathResponseDTO> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String imagePath = this.storageService.store(file);
        ImageAsset asset = new ImageAsset();
        asset.setPath(imagePath);
        asset.setBelongsTo(0);
        images.save(asset);
        return ResponseEntity.status(HttpStatus.OK).body(new PathResponseDTO(imagePath));
    }
}
