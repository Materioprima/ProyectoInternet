/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author materio
 */
@Entity
public class Usuario implements Serializable {

    
    public enum Rol 
    {
      ADMINISTRADOR,            // Acceso global.
      NORMAL,                   //Acceso información básica. 
                                //Usuario estándar.
      COORDINADOR_HONDURAS,     //Acceso a toda gestión Honduras.
      COORDINADOR_ESPAÑA,       //Acceso a toda gestión España.
      GESTOR_FINANCIERO_GLOBAL, //Acceso a toda la gestión financiera.
      COORDINADOR_PROYECTO,     //Acceso a toda la gestión de proyectos.
      COORDINADOR_SEDE_LOCAL_ESPAÑA, //Acceso a la información 
                                     //correspondiente a la sede de 
                                     //España.
      AGENTE,                        //Personal administrativo con información básica.
      COORDINADOR_LOCAL_HONDURAS,    //Acceso a la información correspondiente a 
                                     //la sede de España.
      GESTOR_FINANCIERO_LOCAL        //Acceso a la información correspondiente a la 
                                     //gestión financiera local.
    };
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellidos;
    private String email;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    
    @Id
    private String cuenta;
    private String contrasenia;
    
    private String cadenaValidacion;

    public String getCadenaValidacion() {
        return cadenaValidacion;
    }

    public void setCadenaValidacion(String cadenaValidacion) {
        this.cadenaValidacion = cadenaValidacion;
    }
    /*
    @OrderBy ("id ASC")
    private List<Academico> academicos;

    public List<Academico> getAcademicos() {
        return academicos;
    }

    public void setAcademicos(List<Academico> academicos) {
        this.academicos = academicos;
    }

*/
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol(){
        return this.rol;
    }
    
    public void setRol(Rol rol){
        this.rol=rol;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.cuenta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidadesFinal.Usuario[ cuenta=" + cuenta + " ]";
    }
    
}
