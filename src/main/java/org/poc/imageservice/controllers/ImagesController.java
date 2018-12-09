package org.poc.imageservice.controllers;

import org.poc.imageservice.domain.ImageAsset;
import org.poc.imageservice.dtos.PathResponseDTO;
import org.poc.imageservice.repositories.ImagesRepository;
import org.poc.imageservice.services.StorageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ImagesController {

    private ImagesRepository images;

    private StorageService storageService;

    @Value("${images.path}")
    private String folderPath;

    public ImagesController(ImagesRepository repository, StorageService storageService){
        this.images = repository;
        this.storageService = storageService;
    }

    @GetMapping("/all")
    public List<ImageAsset> getAll(){
        return images.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<PathResponseDTO> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String imagePath = this.storageService.store(file);
        ImageAsset asset = new ImageAsset();
        asset.setPath(imagePath);
        asset.setBelongsTo(0);
        String id =images.save(asset).getId();
        return ResponseEntity.status(HttpStatus.OK).body(new PathResponseDTO(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) throws IOException {
        //TODO: Warning:(49, 48) 'Optional.get()' without 'isPresent()' check
        String imagePath = images.findById(id).get().getPath();
        FileSystemResource fileSystemResources = new FileSystemResource( folderPath + imagePath);
        byte[] bytes = StreamUtils.copyToByteArray(fileSystemResources.getInputStream());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}
