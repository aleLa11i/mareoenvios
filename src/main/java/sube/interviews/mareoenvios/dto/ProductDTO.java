package sube.interviews.mareoenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
