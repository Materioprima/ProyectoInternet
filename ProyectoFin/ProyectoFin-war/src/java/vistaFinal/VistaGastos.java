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
import entidadesFinal.Gastos;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "gastos")
@RequestScoped
public class VistaGastos {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Gastos gastos;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaGastos(){
        gastos= new Gastos();
        modo = Modo.NOACCION;
    }
    
    public Gastos getGastos(){
        return gastos;
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
    
    public String modificar(Gastos a) {
        gastos = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionGastos.xhtml";
    }
    
    public String eliminar(Gastos a) throws FinalException {
        try {
            negocio.eliminarGastos(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setSocios(Gastos g) {
        this.gastos = g;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarProyectos() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionGastos.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarGastos(gastos);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+gastos);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarGastos(gastos);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/gastos.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
