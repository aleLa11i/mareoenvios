package sube.interviews.mareoenvios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductToReportDTO implements Comparable {

    private Float weight;
    private String description;
    private Long totalAmount;

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int compareTo(Object o) {
        ProductToReportDTO product = (ProductToReportDTO) o;
        return this.getTotalAmount().compareTo(product.getTotalAmount());
    }
}
