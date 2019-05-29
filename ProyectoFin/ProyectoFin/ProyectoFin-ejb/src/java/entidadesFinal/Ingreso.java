/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tualf
 */
@Entity
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_transaccion;
    @Column(unique=true)
    private Long codigo_transaccionNuestro;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(length=30,nullable = false)
    private String procedencia;
    private double Ingreso_Euros,Ingreso_Lempiras,Ingreso_Dolares,Egreso_Euros,Egreso_Lempiras,Egreso_Dolares,Valor_Divisas_Euros,Valor_Divisas_Dolares;
    @Column(length = 500)
    private String descripcion;
    @ManyToOne
    private Proyectos ingresos;
    @ManyToOne
    private Beneficiarios beneficiario;
    @ManyToOne
    private Socios socio;
    @OneToOne
    private OrdenPago ordenpago;

    public Long getId() {
        return codigo_transaccion;
    }

    public void setId(Long codigo_transaccion) {
        this.codigo_transaccion = codigo_transaccion;
    }
    public Long getIdNuestro() {
        return codigo_transaccionNuestro;
    }

    public void setIdNuestro(Long idNuestro) {
        this.codigo_transaccionNuestro = idNuestro;
    }
    public Date getFecha() {
	return fecha;
    }
    public void setFecha(Date fecha) {
    	this.fecha = fecha;
    }
    public Socios getSocio() {
        if(socio==null){
            socio=new Socios();
        }
	return socio;
    }
    public void setSocios(Socios socio) {
    	this.socio = socio;
    }
    public String getProcedencia() {
	return procedencia;
    }
    public void setProcedencia(String procedencia) {
       	this.procedencia = procedencia;
    }
    public double getIngreso_Euros() {
	return Ingreso_Euros;
    }
    public void setIngreso_Euros(double ingreso_Euros) {
	Ingreso_Euros = ingreso_Euros;
    }
    public double getIngreso_Lempiras() {
	return Ingreso_Lempiras;
    }
    public void setIngreso_Lempiras(double ingreso_Lempiras) {
	Ingreso_Lempiras = ingreso_Lempiras;
    }
    public double getIngreso_Dolares() {
    	return Ingreso_Dolares;
    }
    public void setIngreso_Dolares(double ingreso_Dolares) {
	Ingreso_Dolares = ingreso_Dolares;
    }
    public double getEgreso_Euros() {
	return Egreso_Euros;
    }
    public void setEgreso_Euros(double egreso_Euros) {
	Egreso_Euros = egreso_Euros;
    }
    public double getEgreso_Lempiras() {
	return Egreso_Lempiras;
    }
    public void setEgreso_Lempiras(double egreso_Lempiras) {
	Egreso_Lempiras = egreso_Lempiras;
    }
    public double getEgreso_Dolares() {
	return Egreso_Dolares;
    }
    public void setEgreso_Dolares(double egreso_Dolares) {
        Egreso_Dolares = egreso_Dolares;
    }
    public double getValor_Divisas_Euros() {
	return Valor_Divisas_Euros;
    }
    public void setValor_Divisas_Euros(double valor_Divisas_Euros) {
	Valor_Divisas_Euros = valor_Divisas_Euros;
    }
    public double getValor_Divisas_Dolares() {
	return Valor_Divisas_Dolares;
    }
    public void setValor_Divisas_Dolares(double valor_Divisas_Dolares) {
	Valor_Divisas_Dolares = valor_Divisas_Dolares;
    }
    public String getDescripcion() {
	return descripcion;
    }
    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }
    public Proyectos getIngresos() {
        if(ingresos==null){
            ingresos=new Proyectos();
        }
	return ingresos;
    }
    public void setIngresos(Proyectos ingresos) {
	this.ingresos = ingresos;
    }
    public Beneficiarios getBeneficiario() {
        if(beneficiario==null){
            beneficiario=new Beneficiarios();
        }
            return beneficiario;
    }
    public void setBeneficiario(Beneficiarios beneficiario) {
	this.beneficiario = beneficiario;
    }
    public OrdenPago getOrdenpago() {
        if(ordenpago==null){
            ordenpago=new OrdenPago();
        }
	return ordenpago;
    }
    public void setOrdenpago(OrdenPago ordenpago) {
	this.ordenpago = ordenpago;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo_transaccion != null ? codigo_transaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.codigo_transaccion == null && other.codigo_transaccion != null) || (this.codigo_transaccion != null && !this.codigo_transaccion.equals(other.codigo_transaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingreso: "+codigo_transaccionNuestro;
    }
    
}