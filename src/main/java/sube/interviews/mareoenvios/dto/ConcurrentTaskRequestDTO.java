package sube.interviews.mareoenvios.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConcurrentTaskRequestDTO {

    private List<ConcurrentTaskShippingDTO> shippings = new ArrayList<>();
}
