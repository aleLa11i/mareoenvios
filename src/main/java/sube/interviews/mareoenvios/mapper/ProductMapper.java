package sube.interviews.mareoenvios.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import sube.interviews.mareoenvios.dto.ProductDTO;
import sube.interviews.mareoenvios.dto.ProductToReportDTO;
import sube.interviews.mareoenvios.entity.Product;
import sube.interviews.mareoenvios.entity.ShippingItem;

import java.util.List;

@Mapper( componentModel = "spring" )
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping( source = "weight", target = "weight"),
            @Mapping( source = "description", target = "description"),
    })
    @BeanMapping(ignoreByDefault = true)
    @Named("mapProductToDto")
    ProductDTO mapEntityToDto(Product product );

    @Mappings({
            @Mapping( source = "weight", target = "weight"),
            @Mapping( source = "description", target = "description"),
            @Mapping( source = "shippingItems", target = "totalAmount", qualifiedByName = "mapToTotalAmount"),
    })
    @Named("mapToProductToReportDTO")
    ProductToReportDTO mapProductToProductToReportDTO(Product product );

    @IterableMapping(qualifiedByName = "mapProductToDto")
    @Named("mapProductListToDto")
    List<ProductDTO> mapListToDto(List<Product> products);

    @IterableMapping(qualifiedByName = "mapToProductToReportDTO")
    List<ProductToReportDTO> mapListToProductToReportDTO( List<Product> products );

    @Named("mapToTotalAmount")
    default Long mapToTotalAmount( List<ShippingItem> shippingItems ){
        return (Long) shippingItems.stream().mapToLong(ShippingItem::getProductCount).reduce( 0, (subtotal, item) -> subtotal + item);
    }
}
