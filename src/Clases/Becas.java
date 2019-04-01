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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author MrDoblas
 */
@Entity
public class Becas implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean tr;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero_orden;
    @Column(length = 500)
    private String concepto;
    @Column(length = 30)
    private double importe;
    @ManyToOne
    private NinosJovenes nino;

    public boolean getTr(){
        return tr;
    }
    public Date getFecha(){
        return fecha;
    }
    public Long getOrden(){
        return numero_orden;
    }
    public String getConcepto(){
        return concepto;
    }
    public double getImporte(){
        return importe;
    }


    public void setTr(boolean t){
        tr = t;
    }
    public void setFecha(Date f){
        fecha = f;
    }
    public void setOrden(Long o){
        numero_orden = o;
    }
    public void setConcepto(String c){
        concepto = c;
    }
    public void setImporte(int i){
        importe = i;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero_orden != null ? numero_orden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Becas)) {
            return false;
        }
        Becas other = (Becas) object;
        if ((this.numero_orden == null && other.numero_orden != null) || (this.numero_orden != null && !this.numero_orden.equals(other.numero_orden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Becas[ numero_orden=" + numero_orden + " ]";
    }
}
