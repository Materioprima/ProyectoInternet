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

import entidadesFinal.OrdenPago;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "ordenpagos")
@RequestScoped
public class OrdenPagos {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private OrdenPago ordenPago;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public OrdenPagos(){
        ordenPago= new OrdenPago();
        modo = Modo.NOACCION;
    }
    
    public OrdenPago getOrdenPago(){
        return ordenPago;
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
    
    public String modificar(OrdenPago a) {
        ordenPago = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionOrdenPago.xhtml";
    }
    
    public String eliminar(OrdenPago a) throws FinalException {
        try {
            negocio.eliminarOrdenPago(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setOrdenPago(OrdenPago ordenPago) {
        this.ordenPago = ordenPago;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarOrdenPago() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionOrdenPago.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarOrdenPago(ordenPago);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+ordenPago);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarOrdenPago(ordenPago);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/ordenPago.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
