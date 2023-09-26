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
}
