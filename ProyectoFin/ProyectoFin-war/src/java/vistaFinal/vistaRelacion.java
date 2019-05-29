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
import entidadesFinal.Relacion;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "relaciones")
@RequestScoped
public class vistaRelacion {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Relacion relacion;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public vistaRelacion(){
        this.relacion= new Relacion();
        modo = Modo.NOACCION;
    }
    
    public Relacion getRelacion(){
        return relacion;
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
    
    public String modificar(Relacion a) {
        relacion = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionRelacion.xhtml";
    }
    
    public String eliminar(Relacion a) throws FinalException {
        try {
            negocio.eliminarRelacion(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setRelacion(Relacion relacion) {
        this.relacion = relacion;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarRelacion() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionRelacion.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarRelacion(relacion);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+relacion);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarRelacion(relacion);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/relacion.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
