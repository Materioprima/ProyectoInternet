/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author nico-
 */
@Entity
public class Personal implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String dni;
    private String nombre,apellido,cargo;
    private Long salario;
    @ManyToMany (mappedBy = "personal")
    private List<NinosJovenes> encargados;
    @OneToMany(mappedBy ="personal")
    private List<Informes>informes;
    public String getDni() {
	return dni;
    }
    public void setDni(String dni) {
	this.dni = dni;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
	return nombre;
    }
    public void setNombre(String nombre) {
	this.nombre = nombre;
    }
    public String getApellido() {
	return apellido;
    }
    public void setApellido(String apellido) {
	this.apellido = apellido;
    }
    public String getCargo() {
    	return cargo;
    }
    public void setCargo(String cargo) {
	this.cargo = cargo;
    }
    
    public Long getSalario() {
    	return salario;
    }
    public void setSalario(Long saldo) {
	this.salario = saldo;
    }
    public List<Informes> getInformes() {
	return informes;
    }
    public void setInformes(List<Informes> Informes) {
	this.informes = Informes;
    }
    public List<NinosJovenes> getEncargados() {
	return encargados;
    }
    public void setEncargados(List<NinosJovenes> encargados) {
	this.encargados = encargados;
    }
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += (dni != null ? dni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) 
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personal)) 
        {
            return false;
        }
        Personal other = (Personal) object;
        if ((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni))) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dni;
    }
}
