package sube.interviews.mareoenvios.dto;

import lombok.Data;

@Data
public class ShippingStatesDTO {

    private Long id;
    private String state;

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }
}
