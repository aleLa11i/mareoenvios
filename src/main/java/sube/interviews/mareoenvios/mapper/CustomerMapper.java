package sube.interviews.mareoenvios.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import sube.interviews.mareoenvios.dto.CustomerDTO;
import sube.interviews.mareoenvios.dto.CustomerShippingDTO;
import sube.interviews.mareoenvios.entity.Customer;

@Mapper( componentModel = "spring",
        uses = {
        ShippingMapper.class
})
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "city", source = "city"),
            @Mapping(target = "shippings", source = "shippings", qualifiedByName = "mapListToShippingCustomerDto"),
    })
    @BeanMapping(ignoreByDefault = true)
    CustomerDTO mapEntityToDto( Customer customer );

    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "address", source = "address"),
            @Mapping(target = "city", source = "city"),
    })
    @Named("mapToCustomerShippingDTO")
    @BeanMapping(ignoreByDefault = true)
    CustomerShippingDTO mapEntityToCustomerShippingDTO( Customer customer );
}
