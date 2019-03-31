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
    private int num_orden;
    @Column(length=30,nullable = false)
    private String emitida_por;
    @Temporal(TemporalType.DATE)
    private Date fecha_emi;

    public int getNum_Orden() 
    {
        return num_orden;
    }

    public void setNum_Orden(int id) 
    {
        this.num_orden = id;
    }

    @Override
    public int hashCode() {
        return num_orden;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdenPago other = (OrdenPago) obj;
        if (this.num_orden != other.num_orden) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() 
    {
        return "practica3.OrdenPago[ Número de orden =" + num_orden + " ]";
    }
    
}
