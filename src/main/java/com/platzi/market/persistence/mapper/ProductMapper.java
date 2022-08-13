package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.Product;
import com.platzi.market.persistence.entity.ProductoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}) // 2. Se indica la clase Mapper para Category.
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"), // fuente --> destino
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"), // 1. Esta clase ya tiene su propio Mapper.
    })
    Product toProduct(ProductoEntity productoEntity);
    List<Product> toProducts(List<ProductoEntity> productoEntities);
    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    ProductoEntity toProducto(Product product);
}
