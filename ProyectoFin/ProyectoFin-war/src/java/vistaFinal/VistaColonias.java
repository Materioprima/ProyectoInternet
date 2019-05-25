/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaFinal;

/**
 *
 * @author Pedro Doblas
 */
import entidadesFinal.Colonias;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "colonias")
@RequestScoped
public class VistaColonias {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Colonias colonias;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaColonias(){
        colonias= new Colonias();
        modo = Modo.NOACCION;
    }
    
    public Colonias getColonias(){
        return colonias;
    }
    
    public Modo getModo() {
        return modo;
    }

    public void setModo(Modo modo) {
        this.modo = modo;
    }
    
    public String getAccion() {
        switch (modo) {
            case MODIFICAR:
                return "Modificar";
            case INSERTAR:
                return "Insertar";

        }
        return null;
    }
    
    public String modificar(Colonias a) {
        colonias = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionColonias.xhtml";
    }
    
    public String eliminar(Colonias a) throws FinalException {
        try {
            negocio.eliminarColonias(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setSocios(Colonias c) {
        this.colonias = c;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarColonias() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionColonias.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarColonias(colonias);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+colonias);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarColonias(colonias);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/colonias.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
