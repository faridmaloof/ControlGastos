/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.io.Serializable;

/**
 *
 * @author farid
 */
public class Cuenta implements Serializable {

    private Integer id;
    private String nombre;
    private double saldo;
    private boolean esVisible;
    private boolean activo;

    public Cuenta() {
        saldo = 0;
        esVisible = true;
        activo = true;
    }

    public Cuenta(Integer id) {
        this.id = id;
        saldo = 0;
        esVisible = true;
        activo = true;
    }

    public Cuenta(Integer id, String nombre, boolean esVisible, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = 0;
        this.esVisible = esVisible;
        this.activo = activo;
    }

    public Cuenta(Integer id, String nombre, double saldo, boolean esVisible, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.saldo = saldo;
        this.esVisible = esVisible;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getEsVisible() {
        return esVisible;
    }

    public void setEsVisible(boolean esVisible) {
        this.esVisible = esVisible;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Entities.Cuentas[ id=" + id + " ]";
    }
    
}
