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

import entidadesFinal.Usuario;
import entidadesFinal.Usuario.Rol;
import static entidadesFinal.Usuario.Rol.*;
import negocioFinal.FinalException;
import negocioFinal.ContraseniaInvalidaException;
import negocioFinal.CuentaInactivaException;
import negocioFinal.CuentaInexistenteException;
import negocioFinal.Negocio;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author francis
 */
@Named(value = "login")
@RequestScoped
public class Login {

    @Inject
    private Negocio negocio;

    @Inject
    private InfoSesion sesion;

    private Usuario usuario;
    
    

    /**
     * Creates a new instance of login
     */
    public Login() {
        usuario = new Usuario();
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String entrar() {
        try {
            negocio.compruebaLogin(usuario);
            sesion.setUsuario(negocio.refrescarUsuario(usuario));
            sesion.setPagina(InfoSesion.Pagina.HOME);
            return "/index.xhtml";

        } catch (CuentaInexistenteException e) {
            FacesMessage fm = new FacesMessage("La cuenta no existe");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (ContraseniaInvalidaException e) {
            FacesMessage fm = new FacesMessage("La contraseña no es correcta");
            FacesContext.getCurrentInstance().addMessage("login:pass", fm);
        } catch (CuentaInactivaException e) {
            FacesMessage fm = new FacesMessage("La cuenta existe pero no está activa");
            FacesContext.getCurrentInstance().addMessage("login:user", fm);
        } catch (FinalException e) {
            FacesMessage fm = new FacesMessage("Error: " + e);
            FacesContext.getCurrentInstance().addMessage(null, fm);
        }
        return null;
    }

    public String home(){
        String pag="/login/login.xhtml";
        if(sesion.getUsuario()!=null){
            sesion.setPagina(InfoSesion.Pagina.HOME);
            pag="/index.xhtml";
        }
        return pag;
    }
    
    public String socios() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() == ADMINISTRADOR || sesion.getUsuario().getRol() == COORDINADOR_HONDURAS || sesion.getUsuario().getRol() == COORDINADOR_ESPAÑA || sesion.getUsuario().getRol() == COORDINADOR_PROYECTO || sesion.getUsuario().getRol() == COORDINADOR_SEDE_LOCAL_ESPAÑA || sesion.getUsuario().getRol() == COORDINADOR_LOCAL_HONDURAS){
            sesion.setPagina(InfoSesion.Pagina.SOCIOS);
            pag = "/tablas/socios.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String proyectos() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() == ADMINISTRADOR || sesion.getUsuario().getRol() == COORDINADOR_HONDURAS || sesion.getUsuario().getRol() == COORDINADOR_ESPAÑA || sesion.getUsuario().getRol() == GESTOR_FINANCIERO_GLOBAL || sesion.getUsuario().getRol() == COORDINADOR_PROYECTO || sesion.getUsuario().getRol() == COORDINADOR_SEDE_LOCAL_ESPAÑA || sesion.getUsuario().getRol() == AGENTE || sesion.getUsuario().getRol() == COORDINADOR_LOCAL_HONDURAS || sesion.getUsuario().getRol() == GESTOR_FINANCIERO_LOCAL){
            sesion.setPagina(InfoSesion.Pagina.PROYECTOS);
            pag = "/tablas/proyectos.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String ingresos()
    {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != GESTOR_FINANCIERO_GLOBAL && sesion.getUsuario().getRol() != GESTOR_FINANCIERO_LOCAL){
            sesion.setPagina(InfoSesion.Pagina.INGRESOS);
            pag = "/tablas/ingresos.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String personal() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != GESTOR_FINANCIERO_GLOBAL && sesion.getUsuario().getRol() != GESTOR_FINANCIERO_LOCAL){
            sesion.setPagina(InfoSesion.Pagina.PERSONAL);
            pag = "/tablas/personal.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String ordenPago() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != COORDINADOR_PROYECTO && sesion.getUsuario().getRol() != COORDINADOR_SEDE_LOCAL_ESPAÑA && sesion.getUsuario().getRol() != AGENTE && sesion.getUsuario().getRol() != COORDINADOR_LOCAL_HONDURAS){
            sesion.setPagina(InfoSesion.Pagina.ORDENPAGO);
            pag = "/tablas/ordenPago.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String ninosjovenes() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != null){
            sesion.setPagina(InfoSesion.Pagina.NIÑOSJOVENES);
            pag = "/tablas/ninosJovenes.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String relacion() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != null){
            sesion.setPagina(InfoSesion.Pagina.ASIGNACIÓN);
            pag = "/tablas/relacion.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String informes() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() == ADMINISTRADOR || sesion.getUsuario().getRol() == COORDINADOR_HONDURAS || sesion.getUsuario().getRol() == COORDINADOR_ESPAÑA || sesion.getUsuario().getRol() == GESTOR_FINANCIERO_GLOBAL || sesion.getUsuario().getRol() == COORDINADOR_SEDE_LOCAL_ESPAÑA || sesion.getUsuario().getRol() == COORDINADOR_LOCAL_HONDURAS)
        {
            sesion.setPagina(InfoSesion.Pagina.INFORMES);
            pag = "/tablas/informes.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String gastos() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != COORDINADOR_PROYECTO && sesion.getUsuario().getRol() != AGENTE){
            sesion.setPagina(InfoSesion.Pagina.GASTOS);
            pag = "/tablas/gastos.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String colonias() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() == ADMINISTRADOR || sesion.getUsuario().getRol() == COORDINADOR_HONDURAS || sesion.getUsuario().getRol() == COORDINADOR_ESPAÑA || sesion.getUsuario().getRol() == AGENTE || sesion.getUsuario().getRol() == COORDINADOR_PROYECTO){
            sesion.setPagina(InfoSesion.Pagina.COLONIAS);
            pag = "/tablas/colonias.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String beneficiarios() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != COORDINADOR_PROYECTO && sesion.getUsuario().getRol() != AGENTE){
            sesion.setPagina(InfoSesion.Pagina.BENEFICIARIOS);
            pag = "/tablas/beneficiarios.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String becas() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != COORDINADOR_PROYECTO){
            sesion.setPagina(InfoSesion.Pagina.BECAS);
            pag = "/tablas/becas.xhtml";
            return pag;
        }
        return pag;
    }
        
    public String agente() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() != NORMAL && sesion.getUsuario().getRol() != GESTOR_FINANCIERO_GLOBAL && sesion.getUsuario().getRol() != COORDINADOR_PROYECTO && sesion.getUsuario().getRol() != GESTOR_FINANCIERO_LOCAL){
            sesion.setPagina(InfoSesion.Pagina.AGENTE);
            pag = "/tablas/agente.xhtml";
            return pag;
        }
        return pag;
    }
    
    public String academico() {
        String pag = "/login/login.xhtml";
        if (sesion.getUsuario().getRol() == ADMINISTRADOR || sesion.getUsuario().getRol() == COORDINADOR_HONDURAS || sesion.getUsuario().getRol() == COORDINADOR_ESPAÑA || sesion.getUsuario().getRol() == AGENTE){
            sesion.setPagina(InfoSesion.Pagina.ACADEMICO);
            pag = "/tablas/academico.xhtml";
            return pag;
        }
        return pag;
    }
}