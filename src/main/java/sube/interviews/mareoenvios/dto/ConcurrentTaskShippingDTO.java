package sube.interviews.mareoenvios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConcurrentTaskShippingDTO {

    @Valid
    @NotNull(message = "El id del envio no puede ser nulo")
    @JsonProperty("shipping-id")
    private Long shippingId;

    @JsonProperty("time-start-in-seg")
    private Long timeStartInSeg = 0L;

    @NotNull(message = "El estado siguiente no puede ser nulo")
    @Valid
    @JsonProperty("next-state")
    private Boolean nextState;
}
