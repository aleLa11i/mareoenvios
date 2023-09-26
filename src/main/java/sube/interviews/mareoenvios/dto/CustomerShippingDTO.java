package sube.interviews.mareoenvios.dto;

import lombok.Data;

@Data
public class CustomerShippingDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String city;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
