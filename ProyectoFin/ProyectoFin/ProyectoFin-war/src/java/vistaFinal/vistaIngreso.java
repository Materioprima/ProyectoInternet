/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaFinal;

import entidadesFinal.Ingreso;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import negocioFinal.FinalException;
import negocioFinal.Negocio;

@Named(value = "ingreso")
@RequestScoped
public class vistaIngreso {
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;
    
    private Ingreso ingr;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public vistaIngreso(){
        ingr = new Ingreso();
        modo = Modo.NOACCION;
    }
    
    public Ingreso getIngreso(){
        return ingr;
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
    
    public String modificar(Ingreso a) {
        ingr = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionIngresos.xhtml";
    }
    
    public String eliminar(Ingreso a) throws FinalException {
        try {
            negocio.eliminarIngreso(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setIngreso(Ingreso ingres) {
        this.ingr = ingres;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarIngreso() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionIngresos.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarIngreso(ingr);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+ingr);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarIngreso(ingr);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/ingresos.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}