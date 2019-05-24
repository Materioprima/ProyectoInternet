/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocioFinal;

/* import es.uma.informatica.sii.agendaee.entidades.Contacto; */
import entidadesFinal.Academico;
import entidadesFinal.OrdenPago;
import entidadesFinal.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Materio
 */
@Local
public interface Negocio {
    public void registrarUsuario(Usuario u)throws FinalException;
    public void validarCuenta(String cuenta, String validacion) throws FinalException;
    public void compruebaLogin(Usuario u) throws FinalException;
    public Usuario refrescarUsuario(Usuario u) throws FinalException;
    public List<Academico> mostrarAcademico();
    public void eliminarAcademico(Academico a)throws FinalException;
    public void insertarAcademico(Academico a)throws FinalException;
    public void modificarAcademico(Academico a)throws FinalException;
    public List<OrdenPago> mostrarOrdenPago();
    public void eliminarOrdenPago(OrdenPago a)throws FinalException;
    public void insertarOrdenPago(OrdenPago a)throws FinalException;
    public void modificarOrdenPago(OrdenPago a)throws FinalException;
    /*public void modificar(Contacto c) throws AgendaException;
    public void insertar(Contacto c)throws AgendaException;
    public void eliminarContacto(Contacto c)throws AgendaException;*/
}

