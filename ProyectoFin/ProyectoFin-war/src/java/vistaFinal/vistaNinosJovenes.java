/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaFinal;

import entidadesFinal.NinosJovenes;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import negocioFinal.FinalException;
import negocioFinal.Negocio;

@Named(value = "ninosjovenes")
@RequestScoped
public class vistaNinosJovenes {
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private NinosJovenes nj;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public vistaNinosJovenes(){
        nj = new NinosJovenes();
        modo = Modo.NOACCION;
    }
    
    public NinosJovenes getNinosJovenes(){
        return nj;
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
    
    public String modificar(NinosJovenes a) {
        nj = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionAcademico.xhtml";
    }
    
    public String eliminar(NinosJovenes a) throws FinalException {
        try {
            negocio.eliminarNinosJovenes(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setNinosJovenes(NinosJovenes ninosj) {
        this.nj = ninosj;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarAcademico() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionAcademico.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarNinosJovenes(nj);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+nj);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarNinosJovenes(nj);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/academico.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
