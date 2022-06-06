package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository // Estereotipo que especifica clases que interactuan con base de datos.
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstadoIsTrue(quantity);
        return productos.map(prods -> mapper.toProducts(prods)); // Retorna un Optional de la traducción
    }

    @Override
    public Optional<Product> getProduct(int productID) {
        return productoCrudRepository.findById(productID).map(prods -> mapper.toProduct(prods));
    }

    @Override
    public Product save(Product product) {
        return mapper.toProduct(
                productoCrudRepository.save(mapper.toProducto(product)));
    }

    public Optional<List<Producto>> getResagados(double precioVenta, int cantidadStock){
        return productoCrudRepository
                .findByPrecioVentaLessThanAndCantidadStockGreaterThanAndEstadoIsTrue(precioVenta,
                        cantidadStock);
    }

    @Override
    public void delete(int porductID){
        productoCrudRepository.deleteById(porductID);
    }
}
