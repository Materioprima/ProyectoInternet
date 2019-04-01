package Clases;

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
    @Column(length=30,nullable = false)
    private String nombre,apellidos;
    private String estado,sexo,grado;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento,fechaEntrada,fechaAltaProyecto,fechaSalidaProyecto,fechaAlta,fechaSalidaAcoes;
    private byte[] foto;
    @Column(length = 500)
    private String observaciones;
    @ManyToOne
    private Socios idSocio;
    @ManyToOne
    private Proyectos proyecto;
    @OneToOne
    private Academico notas;
    @ManyToOne
    private Agente agente;
    @ManyToMany
    private List<Personal> personal;
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
    public String toString() 
    {
        return "practica3.Autor[ id=" + codigo + " ]";
    }
    
}