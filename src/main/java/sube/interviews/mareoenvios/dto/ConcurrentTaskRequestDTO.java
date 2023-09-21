package sube.interviews.mareoenvios.dto;

import jakarta.validation.Valid;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConcurrentTaskRequestDTO {

    @Valid
    private List<ConcurrentTaskShippingDTO> shippings = new ArrayList<>();
}
