package com.platzi.market.persistence;

import com.platzi.market.domain.dto.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.ProductoEntity;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository // Estereotipo que especifica clases que interactuan con base de datos.
public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Autowired
    public ProductoRepository(ProductoCrudRepository productoCrudRepository, ProductMapper mapper) {
        this.productoCrudRepository = productoCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Product> getAll(){
        List<ProductoEntity> productoEntities = (List<ProductoEntity>) productoCrudRepository.findAll();
        return mapper.toProducts(productoEntities);
    }

    @Override
    public Optional<List<Product>> getByCategory(Long categoryId) {
        List<ProductoEntity> productoEntities = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productoEntities));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(Long quantity) {
        Optional<List<ProductoEntity>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstadoIsTrue(quantity);
        return productos.map(prods -> mapper.toProducts(prods)); // Retorna un Optional de la traducción
    }

    @Override
    public Optional<Product> getProduct(Long productID) {
        return productoCrudRepository.findById(productID).map(prods -> mapper.toProduct(prods));
    }

    @Override
    public Product save(Product product) {
        return mapper.toProduct(
                productoCrudRepository.save(mapper.toProducto(product)));
    }

    public Optional<List<ProductoEntity>> getResagados(double precioVenta, Long cantidadStock){
        return productoCrudRepository
                .findByPrecioVentaLessThanAndCantidadStockGreaterThanAndEstadoIsTrue(precioVenta,
                        cantidadStock);
    }

    @Override
    public void delete(Long productID){
        productoCrudRepository.deleteById(productID);
    }
}
