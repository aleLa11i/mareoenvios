package sube.interviews.mareoenvios.dto;

import lombok.Data;

@Data
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
