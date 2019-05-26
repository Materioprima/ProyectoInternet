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
import entidadesFinal.Socios;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "socios")
@RequestScoped
public class VistaSocios {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Socios socios;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaSocios(){
        socios= new Socios();
        modo = Modo.NOACCION;
    }
    
    public Socios getSocios(){
        return socios;
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
    
    public String modificar(Socios a) {
        socios = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionSocios.xhtml";
    }
    
    public String eliminar(Socios a) throws FinalException {
        try {
            negocio.eliminarSocios(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setSocios(Socios s) {
        this.socios = s;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarSocios() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionSocios.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarSocios(socios);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+socios);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarSocios(socios);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/socios.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
