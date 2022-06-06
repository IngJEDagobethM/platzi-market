package com.platzi.market.persistence;

import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository // Estereotipo que especifica clases que interactuan con base de datos.
public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public  List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    public Optional<List<Producto>> getEscasos(int cantidadStock){
        return productoCrudRepository.findByCantidadStockLessThanAndEstadoIsTrue(cantidadStock);
    }

    public Optional<List<Producto>> getResagados(double precioVenta, int cantidadStock){
        return productoCrudRepository
                .findByPrecioVentaLessThanAndCantidadStockGreaterThanAndEstadoIsTrue(precioVenta,
                        cantidadStock);
    }

    public Optional<Producto> getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
