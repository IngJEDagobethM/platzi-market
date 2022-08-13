package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.Category;
import com.platzi.market.persistence.entity.CategoriaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T14:08:37-0500",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoriaEntity categoria) {
        if ( categoria == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryId( categoria.getIdCategoria() );
        category.setCategory( categoria.getDescripcion() );
        if ( categoria.getEstado() != null ) {
            category.setActive( Boolean.parseBoolean( categoria.getEstado() ) );
        }

        return category;
    }

    @Override
    public CategoriaEntity toCategoria(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.setIdCategoria( category.getCategoryId() );
        categoriaEntity.setDescripcion( category.getCategory() );
        categoriaEntity.setEstado( String.valueOf( category.isActive() ) );

        return categoriaEntity;
    }
}
