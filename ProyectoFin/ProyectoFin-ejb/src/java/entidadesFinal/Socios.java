/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;

import java.io.Serializable;
import java.util.ArrayList;
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

/**
 *
 * @author nico-
 */
@Entity
public class Socios implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NSocio;
    @Column(unique=true)
    private Long NSocioNuestro;
    @Column(length=30,nullable = false)
    private String nombre,apellidos,dni;
    private String estado,sexo,grado,direccion,poblacion,codigoPostal,provincia,telefonoMovil,telefonoFijo,email,relacion,certificado,sector;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta,fechaBaja;
    @Column(length = 500)
    private String observaciones;
    @OneToMany (mappedBy = "idSocio")
    private List<NinosJovenes> apadrinados;
    @OneToMany (mappedBy = "socio")
    private List<Ingreso> ingreso;
    
    public Long getNSocio() {
	return NSocio;
    }
    public void setNSocio(Long nsocio) {
    	this.NSocio = nsocio;
    }
    public Long getNSocioNuestro() {
	return NSocioNuestro;
    }
    public void setNSocioNuestro(Long nsocio) {
    	this.NSocioNuestro = nsocio;
    }
    public String getNombre() {
	return nombre;
    }
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    public String getApellidos() {
    	return apellidos;
    }
    public void setApellidos(String apellidos) {
    	this.apellidos = apellidos;
    }
    public String getDni() {
    	return dni;
    }
    public void setDni(String dni) {
    	this.dni = dni;
    }
    public String getEstado() {
    	return estado;
    }
    public void setEstado(String estado) {
    	this.estado = estado;
    }
    public String getSexo() {
    	return sexo;
    }
    public void setSexo(String sexo) {
	this.sexo = sexo;
    }
    public String getGrado() {
    	return grado;
    }
    public void setGrado(String grado) {
        this.grado = grado;
    }
    public String getDireccion() {
    	return direccion;
    }
    public void setDireccion(String direccion) {
    	this.direccion = direccion;
    }
    public String getPoblacion() {
    	return poblacion;
    }
    public void setPoblacion(String poblacion) {
    	this.poblacion = poblacion;
    }
    public String getCodigoPostal() {
	return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
	this.codigoPostal = codigoPostal;
    }
    public String getProvincia() {
	return provincia;
    }
    public void setProvincia(String provincia) {
    	this.provincia = provincia;
    }
    public String getTelefonoMovil() {
    	return telefonoMovil;
    }
    public void setTelefonoMovil(String telefonoMovil) {
    	this.telefonoMovil = telefonoMovil;
    }
    public String getTelefonoFijo() {
    	return telefonoFijo;
    }
    public void setTelefonoFijo(String telefonoFijo) {
    	this.telefonoFijo = telefonoFijo;
    }
    public String getEmail() {
    	return email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getRelacion() {
    	return relacion;
    }
    public void setRelacion(String relacion) {
    	this.relacion = relacion;
    }
    public String getCertificado() {
    	return certificado;
    }
    public void setCertificado(String certificado) {
    	this.certificado = certificado;
    }
    public String getSector() {
    	return sector;
    }
    public void setSector(String sector) {
      	this.sector = sector;
    }
    public Date getFechaAlta() {
	return fechaAlta;
    }
    public void setFechaAlta(Date fechaAlta) {
    	this.fechaAlta = fechaAlta;
    }
    public Date getFechaBaja() {
    	return fechaBaja;
    }
    public void setFechaBaja(Date fechaBaja) {
    	this.fechaBaja = fechaBaja;
    }
    public String getObservaciones() {
    	return observaciones;
    }
    public void setObservaciones(String observaciones) {
    	this.observaciones = observaciones;
    }
    public List<NinosJovenes> getApadrinados() {
        if(apadrinados==null){
            List<NinosJovenes> n=new ArrayList();
            apadrinados=n;
        }
        return apadrinados;
    }
    public void setApadrinados(List<NinosJovenes> apadrinados) {
    	this.apadrinados = apadrinados;
    }
    public List<Ingreso> getIngreso() {
        if(ingreso==null){
            List<Ingreso> n=new ArrayList();
            ingreso=n;
        }    	
        return ingreso;
    }
    public void setIngreso(List<Ingreso> ingreso) {
    	this.ingreso = ingreso;
    }
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (NSocio != null ? NSocio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
    // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Socios)) 
        {
            return false;
        }
        Socios other = (Socios) object;
        if ((this.NSocio == null && other.NSocio != null) || (this.NSocio != null && !this.NSocio.equals(other.NSocio))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Socio: "+NSocioNuestro;
    }
}