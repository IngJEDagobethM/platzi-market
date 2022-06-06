package com.platzi.market.persistence.crud;

import com.platzi.market.persistence.entity.Producto;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

// Hereda de CrudRepository el estereotipo @NoRepositoryBean, por lo tanto, es posible inyectar esta clase.
public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    // Con la anotación @Query escribimos nuestras propias sentencias
    // y el nombre del método no debe responder a la caracteristica QueryMethods.
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstadoIsTrue(int cantidadStock);
    Optional<List<Producto>> findByPrecioVentaLessThanAndCantidadStockGreaterThanAndEstadoIsTrue(double precioVenta, int cantidadStock);
}
