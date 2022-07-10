package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.Purchase;
import com.platzi.market.persistence.entity.CompraEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {
    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items"),
    })
    Purchase toPurchase(CompraEntity compraEntity);
    List<Purchase> toPurchases(List<CompraEntity> compraEntities);
    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    CompraEntity toCompra(Purchase purchase);
}
