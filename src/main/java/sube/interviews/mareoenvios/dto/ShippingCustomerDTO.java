package sube.interviews.mareoenvios.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ShippingCustomerDTO {

    private String state;
    private Date sendDate;
    private Date arriveDate;
    private Integer priority;

}
