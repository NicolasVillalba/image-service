package org.poc.imageservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class ImageAsset {

    @Id
    private String id;
    private String path;
    private Integer belongsTo;

    public ImageAsset() {
    }

    public ImageAsset(String path, Integer belongsTo) {
        this.path = path;
        this.belongsTo = belongsTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(Integer belongsTo) {
        this.belongsTo = belongsTo;
    }
}
