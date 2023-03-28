/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author farid
 */
public class Movimiento implements Serializable {

    private Integer id;
    private String naturaleza;
    private Date fecha;
    private double valor;
    private Integer idCatergoria;
    private Integer idCuenta;
    private Integer idTag;
    private Integer idTercero;

    public Movimiento() {
        idCatergoria = 0;
        this.idCuenta = 0;
        this.idTag = 0;
        this.idTercero = 0;
    }

    public Movimiento(Integer id) {
        this.id = id;
        this.idCatergoria = 0;
        this.idCuenta = 0;
        this.idTag = 0;
        this.idTercero = 0;
    }

    public Movimiento(Integer id, String naturaleza, Date fecha, double valor, Integer idCuenta) {
        this.id = id;
        this.naturaleza = naturaleza;
        this.fecha = fecha;
        this.valor = valor;
        this.idCuenta = idCuenta;
        this.idCatergoria = 0;
        this.idCuenta = 0;
        this.idTag = 0;
        this.idTercero = 0;
    }

    public Movimiento(Integer id, String naturaleza, Date fecha, double valor, Integer idCatergoria, Integer idCuenta, Integer idTag, Integer idTercero) {
        this.id = id;
        this.naturaleza = naturaleza;
        this.fecha = fecha;
        this.valor = valor;
        this.idCatergoria = idCatergoria;
        this.idCuenta = idCuenta;
        this.idTag = idTag;
        this.idTercero = idTercero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCatergoria() {
        return new DataAccessObjet.Categorias().getById(idCatergoria);
    }

    public Integer getCatergoriaId() {
        return idCatergoria;
    }

    public void setCatergoriaId(Integer idCatergoria) {
        this.idCatergoria = idCatergoria;
    }

    public Cuenta getCuenta() {
        return new DataAccessObjet.Cuentas().getById(idCuenta);
    }

    public Integer getCuentaId() {
        return idCuenta;
    }

    public void setCuentaId(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Tag getTag() {
        return new DataAccessObjet.Tags().getById(idTag);
    }

    public Integer getTagId() {
        return idTag;
    }

    public void setTagId(Integer idTag) {
        this.idTag = idTag;
    }

    public Tercero getTercero() {
        return new DataAccessObjet.Terceros().getById(idTercero);
    }

    public Integer getTerceroId() {
        return idTercero;
    }

    public void setTerceroId(Integer idTercero) {
        this.idTercero = idTercero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Entities.Movimientos[ id=" + id + " ]";
    }
    
}
