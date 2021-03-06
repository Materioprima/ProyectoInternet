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
import javax.persistence.OneToMany;

/**
 *
 * @author tualf
 */
@Entity
public class Colonias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_colonia;
    @Column(unique=true)
    private Long id_coloniaNuestro;
    private String nombre;
    @OneToMany (mappedBy="colonia")
    private List<NinosJovenes> pertenecen;

    public Long getId() {
        return id_colonia;
    }

    public void setId(Long id_colonia) {
        this.id_colonia = id_colonia;
    }
    public Long getIdNuestro() {
        return id_coloniaNuestro;
    }

    public void setIdNuestro(Long id_colonia) {
        this.id_coloniaNuestro = id_colonia;
    }
    public String getNombre() {
	return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<NinosJovenes> getPertenecen() {
	return pertenecen;
    }
    public void setPertenecen(List<NinosJovenes> pertenecen) {
	this.pertenecen = pertenecen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_colonia != null ? id_colonia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colonias)) {
            return false;
        }
        Colonias other = (Colonias) object;
        if ((this.id_colonia == null && other.id_colonia != null) || (this.id_colonia != null && !this.id_colonia.equals(other.id_colonia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Colonias: "+id_coloniaNuestro;
    }
    
}
