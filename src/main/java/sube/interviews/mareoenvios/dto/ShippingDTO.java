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

    public void setCustomer(CustomerShippingDTO customer) {
        this.customer = customer;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
