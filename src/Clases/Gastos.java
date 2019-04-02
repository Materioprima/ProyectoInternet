/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Materio
 */
@Entity
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 500)
    private double Carburante, Mantenimiento, Contenedor, Voluntarios;
    @Column(length=12)
    private int Mes;
    @ManyToOne
    private Proyectos gastos;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCarburante() {
	return Carburante;
    }
    public void setCarburante(double carburante) {
	Carburante = carburante;
    }
    public double getMantenimiento() {
	return Mantenimiento;
    }
    public void setMantenimiento(double mantenimiento) {
	Mantenimiento = mantenimiento;
    }
    public double getContenedor() {
	return Contenedor;
    }
    public void setContenedor(double contenedor) {
	Contenedor = contenedor;
    }
    public double getVoluntarios() {
	return Voluntarios;
    }
    public void setVoluntarios(double voluntarios) {
	Voluntarios = voluntarios;
    }
    public int getMes() {
	return Mes;
    }
    public void setMes(int mes) {
    	Mes = mes;
    }
    public Proyectos getGastos() {
    	return gastos;
    }
    public void setGastos(Proyectos gastos) {
    	this.gastos = gastos;
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
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Gastos[ id=" + id + " ]";
    }
    
}
