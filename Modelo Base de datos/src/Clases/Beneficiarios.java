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
 * @author nico-
 */
@Entity
public class Beneficiarios implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Codigo;
    @Column(length=30,nullable = false)
    private String tipoBeneficiario,numeroCuenta;
    private Boolean habilitado;
    @Column(length = 500)
    private String observaciones;
    @OneToMany(mappedBy = "beneficiario")
    private List<Ingreso>ingresos;
    
    public Long getCodigo() {
	return Codigo;
    }
    public void setCodigo(Long codigo) {
	Codigo = codigo;
    }
    public String getTipoBeneficiario() {
	return tipoBeneficiario;
    }
    public void setTipoBeneficiario(String tipoBeneficiario) {
	this.tipoBeneficiario = tipoBeneficiario;
    }
    public String getNumeroCuenta() {
	return numeroCuenta;
    }
    public void setNumeroCuenta(String numeroCuenta) {
	this.numeroCuenta = numeroCuenta;
    }
    public Boolean getHabilitado() {
	return habilitado;
    }
    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }
    public String getObservaciones() {
	return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public List<Ingreso> getIngresos() {
	return ingresos;
    }
    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }
    
    public int hashCode() {
        int hash = 0;
        hash += (Codigo != null ? Codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficiarios)) {
            return false;
        }
        Beneficiarios other = (Beneficiarios) object;
        if ((this.Codigo == null && other.Codigo != null) || (this.Codigo != null && !this.Codigo.equals(other.Codigo))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Clases.Beneficiarios[ Codigo Beneficiario=" + Codigo + " ]";
    }
}
