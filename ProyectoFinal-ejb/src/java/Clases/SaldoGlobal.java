/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

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
    @Temporal(TemporalType.DATE)
    private Date fecha;
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
    
    public Date getFecha() {
	return fecha;
    }
    public void setFecha(Date fecha) {
	this.fecha = fecha;
    }
    public String getObservaciones() {
	return observaciones;
    }
    public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
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
