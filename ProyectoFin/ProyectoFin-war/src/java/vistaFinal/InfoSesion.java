/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaFinal;

/**
 *
 * @author Materio
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*import es.uma.informatica.sii.agendaee.entidades.Contacto;*/
import entidadesFinal.Academico;
import entidadesFinal.Usuario;
import entidadesFinal.OrdenPago;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author francis
 */
@Named(value = "infoSesion")
@SessionScoped
public class InfoSesion implements Serializable {

    @Inject
    private Negocio negocio;
    private Usuario usuario;
    
    /**
     * Creates a new instance of InfoSesion
     */
    public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
    public synchronized List<Academico> getAcademicos()
    {
        if (usuario != null)
        {
            return negocio.mostrarAcademico();
        }
        return null;
    }
    
    public synchronized List<OrdenPago> getOrdenPagos()
    {
        if (usuario != null)
        {
            return negocio.mostrarOrdenPago();
        }
        return null;
    }
    
    public synchronized String invalidarSesion()
    {
        if (usuario != null)
        {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "/login/login.xhtml";
    }
    
    public synchronized void refrescarUsuario()
    {
        try {
        if (usuario != null)
        {
            usuario = negocio.refrescarUsuario(usuario);
            //System.out.println(usuario.getContactos().size());
        } 
        }
        catch (FinalException e) {
            // TODO
        }
    }
    
}
