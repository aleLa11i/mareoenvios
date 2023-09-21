package sube.interviews.mareoenvios.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import sube.interviews.mareoenvios.dto.ShippingCustomerDTO;
import sube.interviews.mareoenvios.dto.ShippingDTO;
import sube.interviews.mareoenvios.dto.ShippingStatesDTO;
import sube.interviews.mareoenvios.entity.Shipping;

import java.util.List;

@Mapper( uses = { CustomerMapper.class, ProductMapper.class })
public interface ShippingMapper {
    ShippingMapper INSTANCE = Mappers.getMapper(ShippingMapper.class);

    @Mappings({
            @Mapping( source = "customer", target = "customer", qualifiedByName = "mapToCustomerShippingDTO"),
            @Mapping( source = "state", target = "state"),
            @Mapping( source = "sendDate", target = "sendDate"),
            @Mapping( source = "arriveDate", target = "arriveDate"),
            @Mapping( source = "priority", target = "priority"),
            @Mapping( source = "products", target = "products", qualifiedByName = "mapProductListToDto" ),
    })
    @BeanMapping(ignoreByDefault = true)
    ShippingDTO mapEntityToDto( Shipping shipping );

    @Mappings({
            @Mapping( source = "state", target = "state"),
            @Mapping( source = "sendDate", target = "sendDate"),
            @Mapping( source = "arriveDate", target = "arriveDate"),
            @Mapping( source = "priority", target = "priority"),
    })
    @BeanMapping(ignoreByDefault = true)
    @Named("mapToCustomerShippingsDto")
    ShippingCustomerDTO mapEntityToShippingCustomerDTO( Shipping shipping );

    @Mappings({
            @Mapping( source = "id", target = "id"),
            @Mapping( source = "state", target = "state"),
    })
    @BeanMapping(ignoreByDefault = true)
    @Named("mapShippingToStateDto")
    ShippingStatesDTO mapEntityToStateDto( Shipping shipping );

    @IterableMapping( qualifiedByName = "mapToCustomerShippingsDto")
    @Named("mapListToShippingCustomerDto")
    List<ShippingCustomerDTO> mapListToShippingCustomerDto(List<Shipping> shippings );

    @IterableMapping( qualifiedByName = "mapShippingToStateDto")
    List<ShippingStatesDTO> mapListToStateDtoList(List<Shipping> shippings );

}
