/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import entidadesFinal.Becas;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "vistabecas")
@RequestScoped
public class VistaBecas {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Becas becas;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaBecas(){
        becas= new Becas();
        modo = Modo.NOACCION;
    }
    
    public Becas getBecas(){
        return becas;
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
    
    public String modificar(Becas a) {
        becas = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionBecas.xhtml";
    }
    
    public String eliminar(Becas a) throws FinalException {
        try {
            negocio.eliminarBecas(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setAcademico(Becas Becas) {
        this.becas = Becas;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarAcademico() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionBecas.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarBecas(becas);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+becas);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarBecas(becas);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/Becas.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}

