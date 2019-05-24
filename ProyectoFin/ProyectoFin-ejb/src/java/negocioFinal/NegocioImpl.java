/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocioFinal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*import es.uma.informatica.sii.agendaee.entidades.Contacto;*/
import entidadesFinal.Academico;
import entidadesFinal.OrdenPago;
import entidadesFinal.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Materio
 */
@Stateless
public class NegocioImpl implements Negocio {

    private static final int TAM_CADENA_VALIDACION = 20;

    @PersistenceContext(unitName = "ProyectoFin-ejbPU")
    private EntityManager em;

    /**
     *
     * @param u
     * @throws negocioFinal.CuentaRepetidaException
     */
    @Override
    public void registrarUsuario(Usuario u) throws FinalException{
        Usuario user = em.find(Usuario.class, u.getCuenta());
        if (user != null) {
            // El usuario ya existe
            throw new CuentaRepetidaException();
        }

        u.setCadenaValidacion(generarCadenaAleatoria());
        em.persist(u);

        String url_validacion = "http://localhost:8080/ProyectoFin-war/faces/login/validarCuenta.xhtml?cuenta="
                + u.getCuenta() + "&codigoValidacion=" + u.getCadenaValidacion();

        System.out.println(url_validacion);
    }

    private String generarCadenaAleatoria() {
        Random rnd = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TAM_CADENA_VALIDACION; i++) {
            int v = rnd.nextInt(62);
            if (v < 26) {
                sb.append((char) ('a' + v));
            } else if (v < 52) {
                sb.append((char) ('A' + v - 26));
            } else {
                sb.append((char) ('0' + v - 52));
            }
        }

        return sb.toString();

    }

    @Override
    public void validarCuenta(String cuenta, String validacion) throws FinalException{
        Usuario u = em.find(Usuario.class, cuenta);
        if (u == null) {
            throw new CuentaInexistenteException();
        }

        if (u.getCadenaValidacion() == null) {
            // la cuenta ya está activa
            return;
        }

        if (!u.getCadenaValidacion().equals(validacion)) {
            throw new ValidacionIncorrectaException();
        }
        // else
        // Eliminamos la cadena de validación, indicando que ya está activa la cuenta
        u.setCadenaValidacion(null);
    }

    /**
     * Este método debe comprobar que el nombre de usuario y contraseña que
     * recibe en el objeto u pertenecen a un usuario que existe en la BBDD y que
     * está validado (un usuario está validado cuando su cadena de validación es
     * nula).
     * 
     * Puede lanzar las excepciones CuentaInexistenteException, CuentaInactivaException
     * y ContraseniaInvalidaException
     *
     * @param u
     * @throws negocioFinal.FinalException
     */
    @Override
    public void compruebaLogin(Usuario u)  throws FinalException {
        // TODO
        Usuario us = em.find(Usuario.class, u.getCuenta());
        if(us==null){
            throw new CuentaInexistenteException();
        }
        if (u.getCadenaValidacion() != null) {
            throw new CuentaInactivaException();
        }
        if (!(us.getContrasenia().equals(u.getContrasenia()))) {
            throw new ContraseniaInvalidaException();
        }
    }

    /**
     * Este método debe comprobar que el usuario que se le pasa como parámetro
     * es un usuario existente y con contraseña correcta (ya que estamos en la capa
     * de negocio con un Session Bean de tipo @Stateless, debemos comprobar
     * todos los accesos a la capa de nogocio). En caso negativo debe devolver debe devolver 
     * la excepción que corresponda,
     * en caso afirmativo debe devolver una entidad usuario tal con la información
     * existe ahora mismo en la BBDD.
     * @param u
     * @return 
     * @throws negocioFinal.FinalException 
     */
    @Override
    public Usuario refrescarUsuario(Usuario u) throws FinalException {
        // TODO
        compruebaLogin(u);
        Usuario usu = em.find(Usuario.class, u.getCuenta());
        em.refresh(usu);
        return usu;
    }
    
    /**
     * Este método debe actualizar el contacto correspondiente en la BBDD con
     * la información contenida en el objeto que se le pasa como argumento.
     * Antes de eso, debe comprobar que el usuario a quien pertenece el contacto existe y 
     * tiene una contraseña correcta (en caso contrario debe devolver la excepción que
     * corresponda.
     * @param a
     * @param c
     * @return 
     * @throws negocioFinal.FinalException 
     * 
     */
    
    //INICIO ACADEMICO
    
    @Override
    public void modificarAcademico(Academico a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Academico acade = em.find(Academico.class, a.getId());
        acade.setIdNuestro(a.getIdNuestro());
        acade.setFechaPeriodo(a.getFechaPeriodo());
        acade.setNota(a.getNota());
        em.merge(acade);
        
    }
    
    @Override
    public void insertarAcademico(Academico a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Academico acad=new Academico();
        acad.setId(a.getId());
        acad.setIdNuestro(a.getIdNuestro());
        acad.setFechaPeriodo(a.getFechaPeriodo());
        acad.setNota(a.getNota());
        System.out.println("La nota de lo que se inserta es "+a.getNota());
        System.out.println("Objeto creado: "+acad+" objeto insertado: "+a);
        em.persist(acad);
    }
    
    
    @Override
    public void eliminarAcademico(Academico a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Academico academ = em.find(Academico.class, a.getId());
        if(academ!=null){
            em.remove(academ);
        }
    
    }
    
    
    @Override
    public List<Academico> mostrarAcademico(){
        
        try {
            // TODO
            List<Academico> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM ACADEMICO";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Academico e=new Academico();
                e.setId(rs.getLong(1));
                e.setIdNuestro(rs.getLong(3));
                e.setFechaPeriodo(rs.getDate(2));
                e.setNota(rs.getDouble(4));
                resultado.add(e);
            }
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(NegocioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        Query q=em.createNamedQuery("academico.findAll");
        return q.getResultList();*/ 
        return null;
    }
    
    
    //FIN ACADEMICO
    
    //INICIO ORDENPAGO
    
    
    @Override
    public void modificarOrdenPago(OrdenPago a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        OrdenPago ordenp = em.find(OrdenPago.class, a.getNum_Orden());
        ordenp.setNum_OrdenNuestro(a.getNum_OrdenNuestro());
        ordenp.setEmitida_por(a.getEmitida_por());
        ordenp.setFecha_emi(a.getFecha_emi());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarOrdenPago(OrdenPago a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        OrdenPago orden=new OrdenPago();
        orden.setNum_Orden(a.getNum_Orden());
        orden.setNum_OrdenNuestro(a.getNum_OrdenNuestro());
        orden.setEmitida_por(a.getEmitida_por());
        orden.setFecha_emi(a.getFecha_emi());
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarOrdenPago(OrdenPago a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        OrdenPago ord = em.find(OrdenPago.class, a.getNum_Orden());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<OrdenPago> mostrarOrdenPago(){
        
        try {
            // TODO
            List<OrdenPago> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM OrdenPago";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                OrdenPago e=new OrdenPago();
                e.setNum_Orden(rs.getLong(1));
                e.setNum_OrdenNuestro(rs.getLong(4));
                e.setEmitida_por(rs.getString(2));
                e.setFecha_emi(rs.getDate(3));
                resultado.add(e);
            }
            return resultado;
        } catch (SQLException ex) {
            Logger.getLogger(NegocioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        Query q=em.createNamedQuery("academico.findAll");
        return q.getResultList();*/ 
        return null;
    }
  
//FIN ORDENPAGO
    
}
