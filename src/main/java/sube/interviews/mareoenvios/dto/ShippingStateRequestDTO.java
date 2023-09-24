package sube.interviews.mareoenvios.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShippingStateRequestDTO {

    @Valid
    @NotNull(message = "El estado de transicion no puede ser nulo")
    @NotBlank(message = "El estado de transicion no puede estar vacío")
    private String transition;

}
