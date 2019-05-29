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
import entidadesFinal.Informes;
import entidadesFinal.Socios;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "informes")
@RequestScoped
public class VistaInformes {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Informes informes;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaInformes(){
        informes= new Informes();
        modo = Modo.NOACCION;
    }
    
    public Informes getInformes(){
        return informes;
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
    
    public String modificar(Informes a) {
        informes = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionInformes.xhtml";
    }
    
    public String eliminar(Informes a) throws FinalException {
        try {
            negocio.eliminarInformes(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setInformes(Informes s) {
        this.informes = s;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarInformes() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionInformes.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarInformes(informes);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+informes);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarInformes(informes);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/informes.xhtml";
        } 
        catch (FinalException e) 
        {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
