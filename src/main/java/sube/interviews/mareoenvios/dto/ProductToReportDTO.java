package sube.interviews.mareoenvios.dto;

import lombok.Data;

@Data
public class ProductToReportDTO implements Comparable {

    private Float weight;
    private String description;
    private Long totalAmount;

    @Override
    public int compareTo(Object o) {
        ProductToReportDTO product = (ProductToReportDTO) o;
        return this.getTotalAmount().compareTo(product.getTotalAmount());
    }
}
