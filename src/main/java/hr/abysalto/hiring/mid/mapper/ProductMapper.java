package hr.abysalto.hiring.mid.mapper;

import hr.abysalto.hiring.mid.dto.api.ProductListResponse;
import hr.abysalto.hiring.mid.dto.api.ProductResponse;
import hr.abysalto.hiring.mid.dto.dummy.ExternalProduct;
import hr.abysalto.hiring.mid.dto.dummy.ExternalProductList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(ExternalProduct product);

    @Mapping(target = "products", source = "products")
    ProductListResponse toListResponse(ExternalProductList productList);
}
