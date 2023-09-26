package sube.interviews.mareoenvios.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private List<ShippingCustomerDTO> shippings = new ArrayList<>();

    // Se generan Setters ya que mapstruct es incompatible con Lombok

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

    public void setShippings(List<ShippingCustomerDTO> shippings) {
        this.shippings = shippings;
    }
}
