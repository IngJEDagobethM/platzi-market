package com.platzi.market.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "compras_productos", schema = "public")
public class CompraProductoEntity {
    @EmbeddedId
    private CompraProductoPK id;
    private Long cantidad;
    private double total;
    private Boolean estado;
    @ManyToOne
    @MapsId("idCompra") // Para guardar en cascada se indica la llave primaria de la relación dominante (Compra.idCompra).
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private CompraEntity compraEntity;
    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private ProductoEntity productoEntity;

    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public CompraEntity getCompra() {
        return compraEntity;
    }

    public void setCompra(CompraEntity compraEntity) {
        this.compraEntity = compraEntity;
    }

    public ProductoEntity getProducto() {
        return productoEntity;
    }

    public void setProducto(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }
}
