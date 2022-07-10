package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.Category;
import com.platzi.market.persistence.entity.CategoriaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(CategoriaEntity categoria);  // El nombre del método debe ser [toNameClassToReturn]
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    CategoriaEntity toCategoria(Category category);
}
