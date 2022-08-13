package com.platzi.market.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes", schema = "public")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCliente;
    /*@Column(name = "identificacion")
    private String identificacion;
    */
    private String nombre;
    private String apellidos;
    private String celular;
    private String direccion;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @OneToMany(mappedBy = "clienteEntity")
    private List<CompraEntity> compraEntities;
    public Long getId() {
        return idCliente;
    }

    public void setId(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
