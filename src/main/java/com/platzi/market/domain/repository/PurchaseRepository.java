package com.platzi.market.domain.repository;

import com.platzi.market.domain.dto.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(Long clientId);
    Purchase save(Purchase purchase);
}
