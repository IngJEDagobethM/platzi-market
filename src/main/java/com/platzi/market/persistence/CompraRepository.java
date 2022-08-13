package com.platzi.market.persistence;

import com.platzi.market.domain.dto.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistence.crud.CompraCrudRepository;
import com.platzi.market.persistence.entity.CompraEntity;
import com.platzi.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private CompraCrudRepository compraCrudRepository;
    private PurchaseMapper mapper;

    @Autowired
    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<CompraEntity>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(Long clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        CompraEntity compraEntity = mapper.toCompra(purchase);
        compraEntity.getProductos().forEach(compraProducto -> compraProducto.setCompra(compraEntity));
        compraCrudRepository.save(compraEntity);
        return mapper.toPurchase(compraEntity);
    }
}
