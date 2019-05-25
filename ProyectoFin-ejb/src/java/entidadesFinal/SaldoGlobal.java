/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SaldoGlobal implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private Long idNuestro;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String sede;
    @Column(length = 500)
    private String observaciones;
    @OneToMany(mappedBy ="saldo")
    private List<Proyectos>proyectos;

    public Long getId() 
    {
        return id;
    }

    public void setNum_Orden(Long id) 
    {
        this.id = id;
    }
    public Long getIdNuestro() {
        return idNuestro;
    }

    public void setIdNuestro(Long idNuestro) {
        this.idNuestro = idNuestro;
    }    
    public Date getFecha() {
	return fecha;
    }
    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }
    public String getSede() {
	return sede;
    }
    public void setSede(String Sede) {
	this.sede = Sede;
    }
    public String getObservaciones() {
	return observaciones;
    }
    public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
    }
    public List<Proyectos> getProyecto() {
	return proyectos;
    }
    public void setSede(List<Proyectos> proyecto) {
	this.proyectos = proyecto;
    }
        
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaldoGlobal)) 
        {
            return false;
        }
        SaldoGlobal other = (SaldoGlobal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.SaldoGlobal[ id de cuenta=" + id + " ]";
    }
    
}