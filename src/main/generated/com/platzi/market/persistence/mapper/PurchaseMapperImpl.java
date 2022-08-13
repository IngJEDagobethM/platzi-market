package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.dto.Purchase;
import com.platzi.market.domain.dto.PurchaseItem;
import com.platzi.market.persistence.entity.CompraEntity;
import com.platzi.market.persistence.entity.CompraProductoEntity;
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
public class PurchaseMapperImpl implements PurchaseMapper {

    @Autowired
    private PurchaseItemMapper purchaseItemMapper;

    @Override
    public Purchase toPurchase(CompraEntity compraEntity) {
        if ( compraEntity == null ) {
            return null;
        }

        Purchase purchase = new Purchase();

        purchase.setPurchaseId( compraEntity.getIdCompra() );
        purchase.setClientId( compraEntity.getIdCliente() );
        purchase.setDate( compraEntity.getFecha() );
        purchase.setPaymentMethod( compraEntity.getMedioPago() );
        purchase.setComment( compraEntity.getComentario() );
        purchase.setState( compraEntity.getEstado() );
        purchase.setItems( compraProductoEntityListToPurchaseItemList( compraEntity.getProductos() ) );

        return purchase;
    }

    @Override
    public List<Purchase> toPurchases(List<CompraEntity> compraEntities) {
        if ( compraEntities == null ) {
            return null;
        }

        List<Purchase> list = new ArrayList<Purchase>( compraEntities.size() );
        for ( CompraEntity compraEntity : compraEntities ) {
            list.add( toPurchase( compraEntity ) );
        }

        return list;
    }

    @Override
    public CompraEntity toCompra(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        CompraEntity compraEntity = new CompraEntity();

        compraEntity.setIdCompra( purchase.getPurchaseId() );
        compraEntity.setIdCliente( purchase.getClientId() );
        compraEntity.setFecha( purchase.getDate() );
        compraEntity.setMedioPago( purchase.getPaymentMethod() );
        compraEntity.setComentario( purchase.getComment() );
        compraEntity.setEstado( purchase.getState() );
        compraEntity.setProductos( purchaseItemListToCompraProductoEntityList( purchase.getItems() ) );

        return compraEntity;
    }

    protected List<PurchaseItem> compraProductoEntityListToPurchaseItemList(List<CompraProductoEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<PurchaseItem> list1 = new ArrayList<PurchaseItem>( list.size() );
        for ( CompraProductoEntity compraProductoEntity : list ) {
            list1.add( purchaseItemMapper.toPurchaseItem( compraProductoEntity ) );
        }

        return list1;
    }

    protected List<CompraProductoEntity> purchaseItemListToCompraProductoEntityList(List<PurchaseItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CompraProductoEntity> list1 = new ArrayList<CompraProductoEntity>( list.size() );
        for ( PurchaseItem purchaseItem : list ) {
            list1.add( purchaseItemMapper.toCompraProducto( purchaseItem ) );
        }

        return list1;
    }
}
