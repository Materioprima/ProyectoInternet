/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaFinal;

import entidadesFinal.Personal;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import negocioFinal.FinalException;
import negocioFinal.Negocio;

@Named(value = "personal")
@RequestScoped
public class vistaPersonal {
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Personal pers;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public vistaPersonal(){
        pers = new Personal();
        modo = Modo.NOACCION;
    }
    
    public Personal getPersonal(){
        return pers;
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
    
    public String modificar(Personal a) {
        pers = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionPersonal.xhtml";
    }
    
    public String eliminar(Personal a) throws FinalException {
        try {
            negocio.eliminarPersonal(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setNinosJovenes(Personal ninosj) {
        this.pers = ninosj;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarPersonal() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionPersonal.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarPersonal(pers);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+pers);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarPersonal(pers);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/personal.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
