package sube.interviews.mareoenvios.dto;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShippingDTO {

    private CustomerShippingDTO customer;
    private String state;
    private Date sendDate;
    private Date arriveDate;
    private Integer priority;
    private List<ProductDTO> products = new ArrayList<ProductDTO>();

}
