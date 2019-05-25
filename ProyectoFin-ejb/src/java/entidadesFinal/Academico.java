/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;

/**
 *
 * @author Materio
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Materio
 */
@Entity
public class Academico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private Long idNuestro;
    @Temporal(TemporalType.DATE)
    private Date fechaPeriodo;
    @Column(length=30,nullable = false)
    private double nota;
    @ManyToOne
    private NinosJovenes ninoN;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getIdNuestro() {
        return idNuestro;
    }

    public void setIdNuestro(Long idNuestro) {
        this.idNuestro = idNuestro;
    }
    
    public Date getFechaPeriodo() {
	return fechaPeriodo;
    }
    
    public void setFechaPeriodo(Date fechaPeriodo) {
	this.fechaPeriodo = fechaPeriodo;
    } 
    
    public NinosJovenes getNino() {
	return ninoN;
    }
    
    public void setNinosJovenes(NinosJovenes NinosJovenes) {
	this.ninoN = NinosJovenes;
    } 
    
    public double getNota() {
	return nota;
    }
    
    public void setNota(double nota) {
	this.nota = nota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Academico)) {
            return false;
        }
        Academico other = (Academico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Academico[ id=" + id +" idNuestro="+idNuestro+ " periodo="+fechaPeriodo+" nota="+nota+" ]";
    }
    
}
