package entidadesFinal;

/**
 * @author nico-
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class NinosJovenes implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    @Column(unique=true)
    private Long codigoNuestro;
    @Column(length=30,nullable = false)
    private String nombre,apellidos;
    private String estado,sexo,grado;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento,fechaEntrada,fechaAltaProyecto,fechaSalidaProyecto,fechaAlta,fechaSalidaAcoes;
    @Column(length = 500)
    private String observaciones;
    @ManyToOne
    private Socios idSocio;
    @ManyToOne
    private Proyectos proyecto;
    @OneToMany (mappedBy = "ninoN")
    private List<Academico> notas;
    @ManyToOne
    private Agente agente;
    @OneToMany (mappedBy = "nino")
    private List<Relacion> personal;
    @ManyToOne
    private Colonias colonia;
    @OneToMany (mappedBy = "nino")
    private List<Becas> becas;

    public Long getCodigo() 
    {
        return codigo;
    }

    public void setCodigo(Long id) 
    {
        this.codigo = id;
    }
    public Long getCodigoNuestro() {
        return codigoNuestro;
    }

    public void setCodigoNuestro(Long idNuestro) {
        this.codigoNuestro = idNuestro;
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
    public Date getFechaNacimiento() {
	return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }
    public Date getFechaEntrada() {
	return fechaEntrada;
    }
    public void setFechaEntrada(Date fechaEntrada) {
    	this.fechaEntrada = fechaEntrada;
    }
    public Date getFechaAltaProyecto() {
    	return fechaAltaProyecto;
    }
    public void setFechaAltaProyecto(Date fechaAltaProyecto) {
	this.fechaAltaProyecto = fechaAltaProyecto;
    }
    public Date getFechaSalidaProyecto() {
    	return fechaSalidaProyecto;
    }
    public void setFechaSalidaProyecto(Date fechaSalidaProyecto) {
    	this.fechaSalidaProyecto = fechaSalidaProyecto;
    }
    public Date getFechaAlta() {
    	return fechaAlta;
    }
    public void setFechaAlta(Date fechaAlta) {
    	this.fechaAlta = fechaAlta;
    }
    public Date getFechaSalidaAcoes() {
    	return fechaSalidaAcoes;
    }
    public void setFechaSalidaAcoes(Date fechaSalidaAcoes) {
    	this.fechaSalidaAcoes = fechaSalidaAcoes;
    }
    public String getObservaciones() {
	return observaciones;
    }
    public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
    }
    public Socios getIdSocio() {
        if(idSocio==null){
            Socios n=new Socios();
            idSocio=n;
        }
    	return idSocio;
    }
    public void setIdSocio(Socios idSocio) {
    	this.idSocio = idSocio;
    }
    public Proyectos getProyecto() {
        if(proyecto==null){
            Proyectos n=new Proyectos();
            proyecto=n;
        }
        return proyecto;
    }
    public void setProyecto(Proyectos proyecto) {
	this.proyecto = proyecto;
    }
    public List<Academico> getNotas() {
    	return notas;
    }
    public void setNotas(List<Academico> notas) {
    	this.notas = notas;
    }
    public Agente getAgente() {
        if(agente==null){
            Agente n=new Agente();
            agente=n;
        }
        return agente;
    }
    public void setAgente(Agente agente) {
    	this.agente = agente;
    }
    public List<Relacion> getPersonal() {
    	return personal;
    }
    public void setPersonal(List<Relacion> personal) {
	this.personal = personal;
    }
    public Colonias getColonia() {
        if(colonia==null){
            Colonias n=new Colonias();
            colonia=n;
        }        
        return colonia;
    }
    public void setColonia(Colonias colonia) {
        this.colonia = colonia;
    }
    public List<Becas> getBecas() { 
        return becas;
    }
    public void setBecas(List<Becas> becas) {
	this.becas = becas;
    }

    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NinosJovenes)) 
        {
            return false;
        }
        NinosJovenes other = (NinosJovenes) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Niño: " + codigoNuestro+" ";
    }
    
}