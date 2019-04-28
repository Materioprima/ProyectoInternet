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

    public Long getNumero_orden() {
	return numero_orden;
    }
    public void setNumero_orden(Long numero_orden) {
	this.numero_orden = numero_orden;
    }
    public double getImporte() {
	return importe;
    }
    public void setImporte(double importe) {
	this.importe = importe;
    }
    public String getConcepto() {
	return concepto;
    }
    public void setConcepto(String concepto) {
	this.concepto = concepto;
    }
    public NinosJovenes getNino() {
	return nino;
    }
    public void setNino(NinosJovenes nino) {
	this.nino = nino;
    }
    public boolean isTr() {
	return tr;
    }
    public void setTr(boolean tr) {
	this.tr = tr;
    }
    public Date getFecha() {
	return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        return "Clases.Becas[ numero de orden=" + numero_orden + " ]";
    }
}
