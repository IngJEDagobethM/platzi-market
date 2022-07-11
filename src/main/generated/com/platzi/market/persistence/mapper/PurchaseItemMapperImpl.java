package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.PurchaseItem;
import com.platzi.market.persistence.entity.CompraProductoEntity;
import com.platzi.market.persistence.entity.CompraProductoPK;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T08:32:52-0500",
    comments = "version: 1.5.0.Final, compiler: javac, environment: Java 11.0.15.1 (Oracle Corporation)"
)
@Component
public class PurchaseItemMapperImpl implements PurchaseItemMapper {

    @Override
    public PurchaseItem toPurchaseItem(CompraProductoEntity producto) {
        if ( producto == null ) {
            return null;
        }

        PurchaseItem purchaseItem = new PurchaseItem();

        purchaseItem.setProductId( productoIdIdProducto( producto ) );
        purchaseItem.setQuantity( producto.getCantidad() );
        if ( producto.getEstado() != null ) {
            purchaseItem.setActive( producto.getEstado() );
        }
        purchaseItem.setTotal( producto.getTotal() );

        return purchaseItem;
    }

    @Override
    public CompraProductoEntity toCompraProducto(PurchaseItem item) {
        if ( item == null ) {
            return null;
        }

        CompraProductoEntity compraProductoEntity = new CompraProductoEntity();

        compraProductoEntity.setId( purchaseItemToCompraProductoPK( item ) );
        compraProductoEntity.setCantidad( item.getQuantity() );
        compraProductoEntity.setEstado( item.isActive() );
        compraProductoEntity.setTotal( item.getTotal() );

        return compraProductoEntity;
    }

    private Long productoIdIdProducto(CompraProductoEntity compraProductoEntity) {
        if ( compraProductoEntity == null ) {
            return null;
        }
        CompraProductoPK id = compraProductoEntity.getId();
        if ( id == null ) {
            return null;
        }
        Long idProducto = id.getIdProducto();
        if ( idProducto == null ) {
            return null;
        }
        return idProducto;
    }

    protected CompraProductoPK purchaseItemToCompraProductoPK(PurchaseItem purchaseItem) {
        if ( purchaseItem == null ) {
            return null;
        }

        CompraProductoPK compraProductoPK = new CompraProductoPK();

        compraProductoPK.setIdProducto( purchaseItem.getProductId() );

        return compraProductoPK;
    }
}
