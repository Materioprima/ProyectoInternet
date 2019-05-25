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
import entidadesFinal.SaldoGlobal;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "saldoglobal")
@RequestScoped
public class VistaSaldoGlobal {
    
    @Inject
    private InfoSesion sesion;
    @Inject
    private Negocio negocio;

    private SaldoGlobal saldo;
    
    public static enum Modo {
        MODIFICAR, 
        INSERTAR,
        NOACCION
    };
    
    private Modo modo;
    
    public VistaSaldoGlobal(){
        saldo= new SaldoGlobal();
        modo = Modo.NOACCION;
    }
    
    public SaldoGlobal getSaldoGlobal(){
        return saldo;
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
    
    public String modificar(SaldoGlobal a) {
        saldo = a;
        setModo(Modo.MODIFICAR);
        return "/edicion/edicionSaldoGlobal.xhtml";
    }
    
    public String eliminar(SaldoGlobal a) throws FinalException {
        try {
            negocio.eliminarSaldoGlobal(a);
            // Refrescar el usuario
            sesion.refrescarUsuario();
        } catch (FinalException e) {
            return "/index.xhtml";
        }
        return null;
    }
    
    public void setSaldoGlobal(SaldoGlobal s) {
        this.saldo = s;
    }
    
    public String refrescar()
    {
        sesion.refrescarUsuario();
        return null;
    }

    public String insertarSaldoGlobal() {
        setModo(Modo.INSERTAR);
        return "/edicion/edicionSaldoGlobal.xhtml";
    }
    
    public String ejecutarAccion() {
        try {
            switch (modo) {
                case MODIFICAR:
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.modificarSaldoGlobal(saldo);
                    break;
                case INSERTAR:
                    System.out.println("Estoy insertando "+saldo);
                    //contacto.setUsuario(sesion.getUsuario());
                    negocio.insertarSaldoGlobal(saldo);
                    break;
            }
            sesion.refrescarUsuario();
            System.out.println("Se hizo la accion "+this.getModo());
            return "/tablas/SaldoGlobal.xhtml";
        } catch (FinalException e) {
            return "/index.xhtml";
        }
    }
    
    public boolean isAutorizadoParaEdicion()
    {
        return sesion.getUsuario() != null;
    }
}
