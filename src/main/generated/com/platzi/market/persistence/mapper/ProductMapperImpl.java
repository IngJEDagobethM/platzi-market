package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.Product;
import com.platzi.market.persistence.entity.ProductoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T14:08:36-0500",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Product toProduct(ProductoEntity productoEntity) {
        if ( productoEntity == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( productoEntity.getIdProducto() );
        product.setName( productoEntity.getNombre() );
        if ( productoEntity.getIdCategoria() != null ) {
            product.setCategoryId( productoEntity.getIdCategoria().intValue() );
        }
        product.setPrice( productoEntity.getPrecioVenta() );
        product.setStock( productoEntity.getCantidadStock() );
        if ( productoEntity.getEstado() != null ) {
            product.setActive( productoEntity.getEstado() );
        }
        product.setCategory( categoryMapper.toCategory( productoEntity.getCategoria() ) );

        return product;
    }

    @Override
    public List<Product> toProducts(List<ProductoEntity> productoEntities) {
        if ( productoEntities == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productoEntities.size() );
        for ( ProductoEntity productoEntity : productoEntities ) {
            list.add( toProduct( productoEntity ) );
        }

        return list;
    }

    @Override
    public ProductoEntity toProducto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();

        productoEntity.setIdProducto( product.getProductId() );
        productoEntity.setNombre( product.getName() );
        productoEntity.setIdCategoria( (long) product.getCategoryId() );
        productoEntity.setPrecioVenta( product.getPrice() );
        productoEntity.setCantidadStock( product.getStock() );
        productoEntity.setEstado( product.isActive() );
        productoEntity.setCategoria( categoryMapper.toCategoria( product.getCategory() ) );

        return productoEntity;
    }
}
