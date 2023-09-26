package sube.interviews.mareoenvios.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ShippingCustomerDTO {

    private String state;
    private Date sendDate;
    private Date arriveDate;
    private Integer priority;

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
}
