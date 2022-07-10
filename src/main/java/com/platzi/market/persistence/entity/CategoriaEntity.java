package com.platzi.market.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias", schema = "public")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;
    private String descripcion;
    private String estado;
    @OneToMany(mappedBy = "categoria") // se mapea el atributo que corresponde a la relación en la clase producto.
    private List<ProductoEntity> productoEntities;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ProductoEntity> getProductos() {
        return productoEntities;
    }

    public void setProductos(List<ProductoEntity> productoEntities) {
        this.productoEntities = productoEntities;
    }
}
