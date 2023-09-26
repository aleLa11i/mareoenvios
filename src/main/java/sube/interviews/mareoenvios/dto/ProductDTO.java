package sube.interviews.mareoenvios.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Float weight;
    private String description;

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
