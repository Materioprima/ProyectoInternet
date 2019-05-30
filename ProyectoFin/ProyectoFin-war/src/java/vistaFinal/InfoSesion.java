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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entidadesFinal.Academico;
import entidadesFinal.Agente;
import entidadesFinal.Becas;
import entidadesFinal.Beneficiarios;
import entidadesFinal.Colonias;
import entidadesFinal.Gastos;
import entidadesFinal.Informes;
import entidadesFinal.Ingreso;
import entidadesFinal.NinosJovenes;
import entidadesFinal.Usuario;
import entidadesFinal.OrdenPago;
import entidadesFinal.Personal;
import entidadesFinal.Proyectos;
import entidadesFinal.Relacion;
import entidadesFinal.Socios;
import negocioFinal.FinalException;
import negocioFinal.Negocio;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;


@Named(value = "infoSesion")
@SessionScoped
public class InfoSesion implements Serializable {

    @Inject
    private Negocio negocio;
    private Usuario usuario;
    public enum Pagina 
    {
      HOME,
      ASIGNACIÓN,            
      SOCIOS,                               
      PROYECTOS,     
      PERSONAL,       
      ORDENPAGO, 
      NIÑOSJOVENES,     
      INGRESOS, 
      GASTOS,
      COLONIAS,          
      BENEFICIARIOS,
      BECAS,                            
      AGENTE,
      ACADEMICO,
      INFORMES
    };
    
    private Pagina pag;
    
    /**
     * Creates a new instance of InfoSesion
     */
    public InfoSesion() {
    }

    public synchronized void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public synchronized Usuario getUsuario() {
        return usuario;
    }
    
    public synchronized void setPagina(Pagina pag) {
        this.pag = pag;
    }

    public synchronized Pagina getPagina() {
        return pag;
    }
    
    public synchronized List<Academico> getAcademicos()
    {
        if (usuario != null)
        {
            return negocio.mostrarAcademico();
        }
        return null;
    }
    
    public synchronized List<Relacion> getRelacion()
    {
        if (usuario != null)
        {
            return negocio.mostrarRelacion();
        }
        return null;
    }
    
    public synchronized List<OrdenPago> getOrdenPagos()
    {
        if (usuario != null)
        {
            return negocio.mostrarOrdenPago();
        }
        return null;
    }
    
    public synchronized List<Agente> getAgente()
    {
        if (usuario != null)
        {
            return negocio.mostrarAgente();
        }
        return null;
    }
    
    public synchronized List<Becas> getBecas()
    {
        if (usuario != null)
        {
            return negocio.mostrarBecas();
        }
        return null;
    }
    
    public synchronized List<Beneficiarios> getBeneficiarios()
    {
        if (usuario != null)
        {
            return negocio.mostrarBeneficiarios();
        }
        return null;
    }
    
    public synchronized List<Colonias> getColonias()
    {
        if (usuario != null)
        {
            return negocio.mostrarColonias();
        }
        return null;
    }
    
    public synchronized List<Gastos> getGastos()
    {
        if (usuario != null)
        {
            return negocio.mostrarGastos();
        }
        return null;
    }
    
    public synchronized List<Proyectos> getProyectos()
    {
        if (usuario != null)
        {
            return negocio.mostrarProyectos();
        }
        return null;
    }
    
    public synchronized List<Socios> getSocios()
    {
        if (usuario != null)
        {
            return negocio.mostrarSocios();
        }
        return null;
    }
    
    public synchronized List<Ingreso> getIngreso()
    {
        if (usuario != null)
        {
            return negocio.mostrarIngreso();
        }
        return null;
    }
    
    public synchronized List<NinosJovenes> getNinosJovenes()
    {
        if (usuario != null)
        {
            return negocio.mostrarNinosJovenes();
        }
        return null;
    }
    
    public synchronized List<Personal> getPersonal()
    {
        if (usuario != null)
        {
            return negocio.mostrarPersonal();
        }
        return null;
    }
    
    public synchronized List<Informes> getInformes()
    {
        if (usuario != null)
        {
            return negocio.mostrarInformes();
        }
        return null;
    }
    
    public synchronized String invalidarSesion()
    {
        if (usuario != null)
        {
            usuario = null;
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "/login/login.xhtml";
    }
    
    public synchronized void refrescarUsuario()
    {
        try {
        if (usuario != null)
        {
            usuario = negocio.refrescarUsuario(usuario);
            //System.out.println(usuario.getContactos().size());
        } 
        }
        catch (FinalException e) {
            // TODO
        }
    }
    
    public synchronized List<String> RealizarConsulta(String consulta) throws FinalException{
        List<String> resultadoConsul=null;
        resultadoConsul=negocio.ConsultarID(consulta);
        return resultadoConsul;
    }
}