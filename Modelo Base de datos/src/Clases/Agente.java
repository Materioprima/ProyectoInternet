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
import javax.persistence.OneToMany;

/**
 *
 * @author MrDoblas
 */
@Entity
public class Agente implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    @Column(length=30,nullable = false)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @OneToMany (mappedBy = "agente")
    private List<NinosJovenes> encargado;
    
    public Long getId() {
        return codigo;
    }

    public void setId(Long codigo) {
        this.codigo = codigo;
    }
    public List<NinosJovenes> getEncargado() {
	return encargado;
    }
	
    public void setEncargado(List<NinosJovenes> encargado) {
	this.encargado = encargado;
    }
	
    public String getDescripcion() {
        return descripcion;
    }
	
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
	
    public String getNombre() {
        return nombre;
    }
	
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agente)) {
            return false;
        }
        Agente other = (Agente) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Agente[ id=" + codigo + " ]";
    }
    
}
