/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocioFinal;

/* import es.uma.informatica.sii.agendaee.entidades.Contacto; */
import entidadesFinal.Academico;
import entidadesFinal.Agente;
import entidadesFinal.Becas;
import entidadesFinal.Beneficiarios;
import entidadesFinal.Colonias;
import entidadesFinal.Gastos;
import entidadesFinal.Informes;
import entidadesFinal.Ingreso;
import entidadesFinal.OrdenPago;
import entidadesFinal.Proyectos;
import entidadesFinal.Socios;
import entidadesFinal.Usuario;
import entidadesFinal.NinosJovenes;
import entidadesFinal.Personal;
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
    
    public List<Agente> mostrarAgente();
    public void eliminarAgente(Agente a)throws FinalException;
    public void insertarAgente(Agente a)throws FinalException;
    public void modificarAgente(Agente a)throws FinalException;
    
    public List<Becas> mostrarBecas();
    public void eliminarBecas(Becas a)throws FinalException;
    public void insertarBecas(Becas a)throws FinalException;
    public void modificarBecas(Becas a)throws FinalException;
    
    public List<Beneficiarios> mostrarBeneficiarios();
    public void eliminarBeneficiarios(Beneficiarios a)throws FinalException;
    public void insertarBeneficiarios(Beneficiarios a)throws FinalException;
    public void modificarBeneficiarios(Beneficiarios a)throws FinalException;
    
    public List<Colonias> mostrarColonias();
    public void eliminarColonias(Colonias a)throws FinalException;
    public void insertarColonias(Colonias a)throws FinalException;
    public void modificarColonias(Colonias a)throws FinalException;
    
    public List<Gastos> mostrarGastos();
    public void eliminarGastos(Gastos a)throws FinalException;
    public void insertarGastos(Gastos a)throws FinalException;
    public void modificarGastos(Gastos a)throws FinalException;
    
    public List<Informes> mostrarInformes();
    public void eliminarInformes(Informes a)throws FinalException;
    public void insertarInformes(Informes a)throws FinalException;
    public void modificarInformes(Informes a)throws FinalException;
    
    public List<Ingreso> mostrarIngreso();
    public void eliminarIngreso(Ingreso a)throws FinalException;
    public void insertarIngreso(Ingreso a)throws FinalException;
    public void modificarIngreso(Ingreso a)throws FinalException;
    
    public List<Socios> mostrarSocios();
    public void eliminarSocios(Socios a)throws FinalException;
    public void insertarSocios(Socios a)throws FinalException;
    public void modificarSocios(Socios a)throws FinalException;

    public List<Proyectos> mostrarProyectos();
    public void eliminarProyectos(Proyectos a)throws FinalException;
    public void insertarProyectos(Proyectos a)throws FinalException;
    public void modificarProyectos(Proyectos a)throws FinalException;
    
    public List<NinosJovenes> mostrarNinosJovenes();
    public void eliminarNinosJovenes(NinosJovenes a)throws FinalException;
    public void insertarNinosJovenes(NinosJovenes a)throws FinalException;
    public void modificarNinosJovenes(NinosJovenes a)throws FinalException;
    
    public List<Personal> mostrarPersonal();
    public void eliminarPersonal(Personal a)throws FinalException;
    public void insertarPersonal(Personal a)throws FinalException;
    public void modificarPersonal(Personal a)throws FinalException;
    
    public List<String> ConsultarID(String consulta)throws FinalException;
    public List<String> ConsultarMas(String consulta,int nValores)throws FinalException;
    /*public void modificar(Contacto c) throws AgendaException;
    public void insertar(Contacto c)throws AgendaException;
    public void eliminarContacto(Contacto c)throws AgendaException;*/
}

