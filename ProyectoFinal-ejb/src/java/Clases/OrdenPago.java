/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OrdenPago implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long num_orden;
    @Column(length=30,nullable = false)
    private String emitida_por;
    @Temporal(TemporalType.DATE)
    private Date fecha_emi;

    public Long getNum_Orden() 
    {
        return num_orden;
    }

    public void setNum_Orden(Long id) 
    {
        this.num_orden = id;
    }

    public String getEmitida_por() {
	return emitida_por;
    }
    public void setEmitida_por(String emitida_por) {
	this.emitida_por = emitida_por;
    }
    public Date getFecha_emi() {
	return fecha_emi;
    }
    public void setFecha_emi(Date fecha_emi) {
            this.fecha_emi = fecha_emi;
    }
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (num_orden != null ? num_orden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenPago)) 
        {
            return false;
        }
        OrdenPago other = (OrdenPago) object;
        if ((this.num_orden == null && other.num_orden != null) || (this.num_orden != null && !this.num_orden.equals(other.num_orden))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.OrdenPago[ Número de orden=" + num_orden + " ]";
    }
}
