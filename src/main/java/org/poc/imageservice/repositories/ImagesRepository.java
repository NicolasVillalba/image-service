package org.poc.imageservice.repositories;

import org.poc.imageservice.domain.ImageAsset;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagesRepository extends MongoRepository<ImageAsset, String> {

}
