/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author nico-
 */
@Entity
public class Proyectos implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Codigo;
    @Column(length=30,nullable = false)
    private String nombre;
    private Boolean enUso;
    private Integer combustible,mantenimiento,contenedor;
    @Column(length = 500)
    private String descripcion;
    @OneToMany(mappedBy = "proyecto")
    private List<NinosJovenes> participan;
    @OneToMany(mappedBy = "ingresos")
    private List<Ingreso> ingresos;
    @OneToMany(mappedBy = "gastos")
    private List<Gastos> gastos;
    @ManyToOne
    private SaldoGlobal saldo;
    
    
    public Long getCodigo() {
	return Codigo;
    }
    public void setCodigo(Long codigo) {
	Codigo = codigo;
    }
    public String getNombre() {
    	return nombre;
    }
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    public Boolean getEnUso() {
    	return enUso;
    }
    public void setEnUso(Boolean enUso) {
    	this.enUso = enUso;
    }
    public Integer getCombustible() {
    	return combustible;
    }
    public void setCombustible(Integer combustible) {
        this.combustible = combustible;
    }
    public Integer getMantenimiento() {
    	return mantenimiento;
    }
    public void setMantenimiento(Integer mantenimiento) {
    	this.mantenimiento = mantenimiento;
    }
    public Integer getContenedor() {
    	return contenedor;
    }
    public void setContenedor(Integer contenedor) {
    	this.contenedor = contenedor;
    }
    public String getDescripcion() {
	return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public List<NinosJovenes> getParticipan() {
    	return participan;
    }
    public void setParticipan(List<NinosJovenes> participan) {
	this.participan = participan;
    }
    public List<Ingreso> getIngresos() {
	return ingresos;
    }
    public void setIngresos(List<Ingreso> ingresos) {
	this.ingresos = ingresos;
    }
    public List<Gastos> getGastos() {
	return gastos;
    }
    public void setGastos(List<Gastos> gastos) {
	this.gastos = gastos;
    }
    
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (Codigo != null ? Codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) 
        {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.Codigo == null && other.Codigo != null) || (this.Codigo != null && !this.Codigo.equals(other.Codigo))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Proyectos[ Codigo del proyecto=" + Codigo + " ]";
    }
}
