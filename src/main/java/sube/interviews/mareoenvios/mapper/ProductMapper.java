package sube.interviews.mareoenvios.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import sube.interviews.mareoenvios.dto.ProductDTO;
import sube.interviews.mareoenvios.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping( source = "weight", target = "weight"),
            @Mapping( source = "description", target = "description"),
    })
    @BeanMapping(ignoreByDefault = true)
    @Named("mapProductToDto")
    ProductDTO mapEntityToDto(Product product );

    @IterableMapping(qualifiedByName = "mapProductToDto")
    @Named("mapProductListToDto")
    List<ProductDTO> mapListToDto(List<Product> products);
}
