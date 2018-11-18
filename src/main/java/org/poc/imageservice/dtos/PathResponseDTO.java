package org.poc.imageservice.dtos;

public class PathResponseDTO {

    private String path;

    public PathResponseDTO(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
