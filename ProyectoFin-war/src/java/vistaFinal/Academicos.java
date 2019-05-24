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
import entidadesFinal.Academico;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "academicos")
@RequestScoped
public class Academicos {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Academico academico;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public Academicos(){
        academico= new Academico();
        modo = Modo.NOACCION;
    }
    
    public Academico getAcademico(){
        return academico;
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
    
    public String modificar(Academico a) {
        academico = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionAcademico.xhtml";
    }
    
    public String eliminar(Academico a) throws FinalException {
        try {
            negocio.eliminarAcademico(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setAcademico(Academico academico) {
        this.academico = academico;
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
                    negocio.modificarAcademico(academico);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+academico);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarAcademico(academico);
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
