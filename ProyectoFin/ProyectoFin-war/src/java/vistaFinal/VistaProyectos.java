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
import entidadesFinal.Proyectos;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "proyectos")
@RequestScoped
public class VistaProyectos {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Proyectos proyectos;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaProyectos(){
        proyectos= new Proyectos();
        modo = Modo.NOACCION;
    }
    
    public Proyectos getProyectos(){
        return proyectos;
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
    
    public String modificar(Proyectos a) {
        proyectos = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionProyectos.xhtml";
    }
    
    public String eliminar(Proyectos a) throws FinalException {
        try {
            negocio.eliminarProyectos(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setProyectos(Proyectos p) {
        this.proyectos = p;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarProyectos() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionProyectos.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarProyectos(proyectos);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+proyectos);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarProyectos(proyectos);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/proyectos.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
