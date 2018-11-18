package org.poc.imageservice;

import org.poc.imageservice.domain.ImageAsset;
import org.poc.imageservice.repositories.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Seeder {

    private ImagesRepository images;

    @Autowired
    public void setImages(ImagesRepository images) {
        this.images = images;
    }

    @EventListener
    public void insertSomeDate(ContextRefreshedEvent event){
        List<ImageAsset> l = Arrays.asList(
                new ImageAsset("saksjaksasksa", 334),
                new ImageAsset("saksjaksasksa", 324),
                new ImageAsset("saksjaksasksa", 314),
                new ImageAsset("saksjaksasksa", 234)
        );
        images.saveAll(l);
    }
}
