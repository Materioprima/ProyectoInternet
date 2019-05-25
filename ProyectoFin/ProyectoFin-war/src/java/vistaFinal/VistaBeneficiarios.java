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
import entidadesFinal.Beneficiarios;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "vistabeneficiarios")
@RequestScoped
public class VistaBeneficiarios {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private Beneficiarios beneficiarios;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaBeneficiarios(){
        beneficiarios= new Beneficiarios();
        modo = Modo.NOACCION;
    }
    
    public Beneficiarios getBeneficiarios(){
        return beneficiarios;
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
    
    public String modificar(Beneficiarios a) {
        beneficiarios = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionBeneficiarios.xhtml";
    }
    
    public String eliminar(Beneficiarios a) throws FinalException {
        try {
            negocio.eliminarBeneficiarios(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setBeneficiarios(Beneficiarios beneficiarios) {
        this.beneficiarios = beneficiarios;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarBeneficiarios() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionBeneficiarios.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarBeneficiarios(beneficiarios);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+beneficiarios);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarBeneficiarios(beneficiarios);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/beneficiarios.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}

