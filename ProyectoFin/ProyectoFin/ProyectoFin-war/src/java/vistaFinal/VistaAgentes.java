/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaFinal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entidadesFinal.Academico;
import entidadesFinal.Agente;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "agente")
@RequestScoped
public class VistaAgentes {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Agente agente;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaAgentes(){
        agente= new Agente();
        modo = Modo.NOACCION;
    }
    
    public Agente getAgentes(){
        return agente;
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
    
    public String modificar(Agente a) {
        agente = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionAgente.xhtml";
    }
    
    public String eliminar(Agente a) throws FinalException {
        try {
            negocio.eliminarAgente(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setAcademico(Agente Agente) {
        this.agente = Agente;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarAgente() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionAgente.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarAgente(agente);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+agente);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarAgente(agente);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/agente.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
