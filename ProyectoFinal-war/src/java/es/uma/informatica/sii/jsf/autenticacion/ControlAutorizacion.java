/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.informatica.sii.jsf.autenticacion;

import es.uma.informatica.sii.jsf.autenticacion.modelo.Usuario;
import static es.uma.informatica.sii.jsf.autenticacion.modelo.Usuario.Rol.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author francis
 */
@Named(value = "controlAutorizacion")
@SessionScoped
public class ControlAutorizacion implements Serializable {

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String home() {
        String pag = "login.xhtml";
        if(usuario!=null){
            if ((usuario.getRol()== NORMAL)||(usuario.getRol() == ADMINISTRADOR)) {
                pag = "normal.xhtml";
            }
        }
        return pag;
    }
    
    public String socios() {
        String pag = "login.xhtml";
        if (usuario.getRol() == ADMINISTRADOR || usuario.getRol() == COORDINADOR_HONDURAS || usuario.getRol() == COORDINADOR_ESPAÑA || usuario.getRol() == COORDINADOR_PROYECTO || usuario.getRol() == COORDINADOR_SEDE_LOCAL_ESPAÑA || usuario.getRol() == COORDINADOR_LOCAL_HONDURAS){
            pag = "socios.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String saldoGlobal() {
        String pag = "login.xhtml";
        if (usuario.getRol() == ADMINISTRADOR || usuario.getRol() == COORDINADOR_HONDURAS || usuario.getRol() == COORDINADOR_ESPAÑA || usuario.getRol() == GESTOR_FINANCIERO_GLOBAL || usuario.getRol() == COORDINADOR_SEDE_LOCAL_ESPAÑA || usuario.getRol() == COORDINADOR_LOCAL_HONDURAS){
            pag = "saldoGlobal.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String proyectos() {
        String pag = "login.xhtml";
        if (usuario.getRol() == ADMINISTRADOR || usuario.getRol() == COORDINADOR_HONDURAS || usuario.getRol() == COORDINADOR_ESPAÑA || usuario.getRol() == GESTOR_FINANCIERO_GLOBAL || usuario.getRol() == COORDINADOR_PROYECTO || usuario.getRol() == COORDINADOR_SEDE_LOCAL_ESPAÑA || usuario.getRol() == AGENTE || usuario.getRol() == COORDINADOR_LOCAL_HONDURAS || usuario.getRol() == GESTOR_FINANCIERO_LOCAL){
            pag = "proyectos.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String personal() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != GESTOR_FINANCIERO_GLOBAL && usuario.getRol() != GESTOR_FINANCIERO_LOCAL){
            pag = "personal.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String ordenPago() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != COORDINADOR_PROYECTO && usuario.getRol() != COORDINADOR_SEDE_LOCAL_ESPAÑA && usuario.getRol() != AGENTE && usuario.getRol() != COORDINADOR_LOCAL_HONDURAS){
            pag = "ordenPago.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String ninosjovenes() {
        String pag = "login.xhtml";
        if (usuario.getRol() != null){
            pag = "ninosJovenes.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String ingresos() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != COORDINADOR_PROYECTO && usuario.getRol() != AGENTE){
            pag = "ingresos.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String gastos() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != COORDINADOR_PROYECTO && usuario.getRol() != AGENTE){
            pag = "gastos.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String colonias() {
        String pag = "login.xhtml";
        if (usuario.getRol() == ADMINISTRADOR || usuario.getRol() == COORDINADOR_HONDURAS || usuario.getRol() == COORDINADOR_ESPAÑA || usuario.getRol() == AGENTE || usuario.getRol() == COORDINADOR_PROYECTO){
            pag = "colonias.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String beneficiarios() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != COORDINADOR_PROYECTO && usuario.getRol() != AGENTE){
            pag = "beneficiarios.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String becas() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != COORDINADOR_PROYECTO){
            pag = "becas.xhtml";
            return pag;
        }
        return pag;
    }
        
    public String agente() {
        String pag = "login.xhtml";
        if (usuario.getRol() != NORMAL && usuario.getRol() != GESTOR_FINANCIERO_GLOBAL && usuario.getRol() != COORDINADOR_PROYECTO && usuario.getRol() != GESTOR_FINANCIERO_LOCAL){
            pag = "agente.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String academico() {
        String pag = "login.xhtml";
        if (usuario.getRol() == ADMINISTRADOR || usuario.getRol() == COORDINADOR_HONDURAS || usuario.getRol() == COORDINADOR_ESPAÑA || usuario.getRol() == AGENTE){
            pag = "academico.xhtml";
            return pag;
        }
        return pag;
    }
    
    
    
    public String logout()
    {
        // Destruye la sesión (y con ello, el ámbito de este bean)
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        usuario = null;
        return "login.xhtml";
    }

    /**
     * Creates a new instance of ControlAutorizacion
     */
    public ControlAutorizacion() {
    }
}
