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
import entidadesFinal.Agente;
import entidadesFinal.Becas;
import entidadesFinal.Beneficiarios;
import entidadesFinal.Colonias;
import entidadesFinal.Gastos;
import entidadesFinal.Informes;
import entidadesFinal.Ingreso;
import entidadesFinal.OrdenPago;
import entidadesFinal.Personal;
import entidadesFinal.Proyectos;
import entidadesFinal.Socios;
import entidadesFinal.Usuario;
import entidadesFinal.NinosJovenes;
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
    
//INICIO Agente
    
    
    @Override
    public void modificarAgente(Agente a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Agente ordenp = em.find(Agente.class, a.getId());
        ordenp.setIdNuestro(a.getIdNuestro());
        ordenp.setDescripcion(a.getDescripcion());
        ordenp.setEncargado(a.getEncargado());
        ordenp.setNombre(a.getNombre());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarAgente(Agente a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Agente orden=new Agente();
        orden.setId(a.getId());
        orden.setIdNuestro(a.getIdNuestro());
        orden.setDescripcion(a.getDescripcion());
        orden.setEncargado(a.getEncargado());
        orden.setNombre(a.getNombre());
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarAgente(Agente a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Agente ord = em.find(Agente.class, a.getId());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Agente> mostrarAgente(){
        
        try {
            // TODO
            List<Agente> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM Agente";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Agente e=new Agente();
                e.setId(rs.getLong(1));
                e.setIdNuestro(rs.getLong(2));
                e.setDescripcion(rs.getString(3));
                e.setNombre(rs.getString(4));
                String query="SELECT CODIGO FROM NINOSJOVENES WHERE AGENTE_CODIGO="+e.getId();
                List<String>Consulta=ConsultarID(query);
                List<NinosJovenes> ninos=new ArrayList();
                if(Consulta.isEmpty()){
                    ninos.add(new NinosJovenes());
                    e.setEncargado(e.getEncargado());
                }else{
                    for(String b:Consulta){
                        System.out.println("SOY UNA BANDERA "+b+" el LONG DA "+Long.parseLong(b));
                        NinosJovenes s=em.find(NinosJovenes.class, Long.parseLong(b));
                        ninos.add(s);
                    }
                }
                e.setEncargado(ninos);
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
  
//FIN Agente
    
//INICIO BECAS
    
    
    @Override
    public void modificarBecas(Becas a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Becas ordenp = em.find(Becas.class, a.getNumero_orden());
        ordenp.setConcepto(a.getConcepto());
        ordenp.setFecha(a.getFecha());
        ordenp.setImporte(a.getImporte());
        ordenp.setNino(a.getNino());
        ordenp.setNumero_ordenNuestro(a.getNumero_ordenNuestro());
        ordenp.setTr(a.isTr());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarBecas(Becas a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Becas orden=new Becas();
        orden.setConcepto(a.getConcepto());
        orden.setFecha(a.getFecha());
        orden.setImporte(a.getImporte());
        orden.setNino(a.getNino());
        orden.setNumero_orden(a.getNumero_orden());
        orden.setNumero_ordenNuestro(a.getNumero_ordenNuestro());
        orden.setTr(a.isTr());
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarBecas(Becas a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Becas ord = em.find(Becas.class, a.getNumero_orden());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Becas> mostrarBecas(){
        
        try {
            // TODO
            List<Becas> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM Becas";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Becas e=new Becas();
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
  
//FIN BECAS
    
//INICIO BECAS
    
    
    @Override
    public void modificarBeneficiarios(Beneficiarios a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Beneficiarios ordenp = em.find(Beneficiarios.class, a.getCodigo());
        ordenp.setCodigoNuestro(a.getCodigoNuestro());
        ordenp.setHabilitado(a.getHabilitado());
        ordenp.setIngresos(a.getIngresos());
        ordenp.setNumeroCuenta(a.getNumeroCuenta());
        ordenp.setObservaciones(a.getObservaciones());
        ordenp.setTipoBeneficiario(a.getTipoBeneficiario());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarBeneficiarios(Beneficiarios a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Beneficiarios orden=new Beneficiarios();
        orden.setCodigo(a.getCodigo());
        orden.setCodigoNuestro(a.getCodigoNuestro());
        orden.setHabilitado(a.getHabilitado());
        orden.setIngresos(a.getIngresos());
        orden.setNumeroCuenta(a.getNumeroCuenta());
        orden.setObservaciones(a.getObservaciones());
        orden.setTipoBeneficiario(a.getTipoBeneficiario());
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarBeneficiarios(Beneficiarios a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Beneficiarios ord = em.find(Beneficiarios.class, a.getCodigo());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Beneficiarios> mostrarBeneficiarios(){
        
        try {
            // TODO
            List<Beneficiarios> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM Beneficiarios";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Beneficiarios e=new Beneficiarios();
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
  
//FIN BECAS
    
//INICIO COLONIAS
    
    
    @Override
    public void modificarColonias(Colonias a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        System.out.println("nombreBanderasaz "+a.getNombre()+" idn "+a.getId());
        Colonias ordenp = em.find(Colonias.class, a.getId());
        ordenp.setIdNuestro(a.getIdNuestro());
        ordenp.setNombre(a.getNombre());
        //ordenp.setPertenecen(a.getPertenecen());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarColonias(Colonias a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Colonias orden=new Colonias();
        orden.setId(a.getId());
        orden.setIdNuestro(a.getIdNuestro());
        orden.setNombre(a.getNombre());
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarColonias(Colonias a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        System.out.println(a);
        Colonias ord = em.find(Colonias.class, a.getId());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Colonias> mostrarColonias(){
        
        try {
            // TODO
            List<Colonias> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM COLONIAS";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next())
            {
                Colonias e=new Colonias();
                e.setId(rs.getLong(1));
                e.setIdNuestro(rs.getLong(2));
                e.setNombre(rs.getString(3));
                String query="SELECT codigo FROM NINOSJOVENES where colonia_id_colonia="+e.getId();
                List<String>Consultadi=ConsultarID(query);
                List<NinosJovenes> ninos=new ArrayList();
                if(Consultadi.isEmpty()){
                    ninos.add(new NinosJovenes());
                    e.setPertenecen(e.getPertenecen());
                }else{
                    for(String b:Consultadi){
                        NinosJovenes s=em.find(NinosJovenes.class, Long.parseLong(b));
                        ninos.add(s);
                    }
                }
                e.setPertenecen(ninos);
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
    
//FIN Colonias
    
//INICIO GASTOS
    
    
    @Override
    public void modificarGastos(Gastos a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Gastos ordenp = em.find(Gastos.class, a.getId());
        ordenp.setIdNuestro(a.getIdNuestro());
        ordenp.setCarburante(a.getCarburante());
        ordenp.setContenedor(a.getContenedor());
        ordenp.setFecha(a.getFecha());
        ordenp.setGastos(a.getGastos());
        ordenp.setMantenimiento(a.getMantenimiento());
        ordenp.setVoluntarios(a.getVoluntarios());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarGastos(Gastos a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Gastos orden=new Gastos();
        orden.setId(a.getId());
        orden.setIdNuestro(a.getIdNuestro());
        orden.setCarburante(a.getCarburante());
        orden.setContenedor(a.getContenedor());
        orden.setFecha(a.getFecha());
        orden.setGastos(a.getGastos());
        orden.setMantenimiento(a.getMantenimiento());
        orden.setVoluntarios(a.getVoluntarios());
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarGastos(Gastos a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Gastos ord = em.find(Gastos.class, a.getId());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Gastos> mostrarGastos(){
        
        try {
            // TODO
            List<Gastos> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM Gastos";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Gastos e=new Gastos();
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
  
//FIN gastos
    
//INICIO INFORMES
    
    
    @Override
    public void modificarInformes(Informes a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Informes ordenp = em.find(Informes.class, a.getId());
        ordenp.setFechaEscritura(a.getFechaEscritura());
        ordenp.setInforme(a.getInforme());
        ordenp.setNumeroInforme(a.getNumeroInforme());
        ordenp.setPersonal(a.getPersonal());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarInformes(Informes a) throws FinalException{
        try {
            // TODO
            //compruebaLogin(c.getUsuario());
            Informes orden=new Informes();
            orden.setId(a.getId());
            orden.setFechaEscritura(a.getFechaEscritura());
            orden.setInforme(a.getInforme());
            orden.setNumeroInforme(a.getNumeroInforme());
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT ID FROM Personal where dni='"+a.getPersonal().getDni()+"'";
            ResultSet rs=st.executeQuery(consulta);
            Long prueba=0L;
            while(rs.next())
            {
                prueba = rs.getLong(1);
            }
            Personal b= new Personal();
            b.setId(prueba);
            b.setDni(a.getPersonal().getDni());
            orden.setPersonal(b);
            System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
            em.persist(orden);
        } catch (SQLException ex) {
            Logger.getLogger(NegocioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void eliminarInformes(Informes a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Informes ord = em.find(Informes.class, a.getId());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Informes> mostrarInformes(){
        
        try {
            // TODO
            List<Informes> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM Informes";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Informes e=new Informes();
                e.setId(rs.getLong(1));
                e.setFechaEscritura(rs.getDate(2));
                e.setInforme(rs.getString(3));
                e.setNumeroInforme(rs.getLong(4));
                System.out.println("ID PER"+rs.getLong(5));
                Connection conn2 = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
                Statement st2=conn2.createStatement();
                String query3="SELECT DNI FROM PERSONAL WHERE ID="+rs.getLong(5);
                ResultSet rs2=st2.executeQuery(query3);
                Personal per= new Personal();
                while(rs2.next())
                {
                    per.setDni(rs2.getString(1));
                }
                e.setPersonal(per);
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
  
//FIN informe
    
//INICIO INGRESO
      @Override
    public void modificarIngreso(Ingreso a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Ingreso ordenp = em.find(Ingreso.class, a.getId());
        //ordenp.setBeneficiario(a.getBeneficiario());
        ordenp.setDescripcion(a.getDescripcion());
        ordenp.setEgreso_Dolares(a.getEgreso_Dolares());
        ordenp.setEgreso_Euros(a.getEgreso_Euros());
        ordenp.setEgreso_Lempiras(a.getEgreso_Lempiras());
        ordenp.setFecha(a.getFecha());
        ordenp.setIdNuestro(a.getIdNuestro());
        ordenp.setIngreso_Dolares(a.getIngreso_Dolares());
        ordenp.setIngreso_Euros(a.getIngreso_Euros());
        ordenp.setIngreso_Lempiras(a.getIngreso_Lempiras());
        //ordenp.setIngresos(a.getIngresos());
        //ordenp.setOrdenpago(a.getOrdenpago());
        ordenp.setProcedencia(a.getProcedencia());
        //ordenp.setSocios(a.getSocio());
        String query="SELECT NSOCIO FROM SOCIOS WHERE NSOCIONUESTRO="+a.getSocio().getNSocioNuestro();
        List<String>Consulta=ConsultarID(query);
        Socios socio=new Socios();
        if(Consulta.isEmpty()){
            socio=new Socios();
        }else{
            for(String b:Consulta){
                Socios s=em.find(Socios.class, Long.parseLong(b));
                socio=s;
            }
        }
        String query2="SELECT CODIGO FROM BENEFICIARIOS WHERE CODIGONUESTRO="+a.getBeneficiario().getCodigoNuestro();
        List<String>Consulta2=ConsultarID(query2);
        Beneficiarios ben=new Beneficiarios();
        if(Consulta2.isEmpty()){
            ben=new Beneficiarios();
        }else{
            for(String b:Consulta2){
                Beneficiarios s=em.find(Beneficiarios.class, Long.parseLong(b));
                ben=s;
            }
        }
        String query3="SELECT CODIGO FROM PROYECTOS WHERE CODIGONUESTRO="+a.getIngresos().getCodigoNuestro();
        List<String>Consulta3=ConsultarID(query3);
        Proyectos ingres= new Proyectos();
        if(Consulta3.isEmpty()){
            ingres= new Proyectos();
        }else{
            for(String b:Consulta3){
                Proyectos s=em.find(Proyectos.class, Long.parseLong(b));
                ingres=s;
            }
        }
        String query4="SELECT NUM_ORDEN FROM ORDENPAGO WHERE NUM_ORDENNUESTRO="+a.getOrdenpago().getNum_OrdenNuestro();
        List<String>Consulta4=ConsultarID(query4);
        OrdenPago ordenpago=new OrdenPago();
        if(Consulta4.isEmpty()){
            ordenpago=new OrdenPago();
        }else{
            for(String b:Consulta4){
                OrdenPago s=em.find(OrdenPago.class, Long.parseLong(b));
                ordenpago=s;
            }
        }
        ordenp.setSocios(socio);
        ordenp.setBeneficiario(ben);
        ordenp.setIngresos(ingres);
        ordenp.setOrdenpago(ordenpago);
        ordenp.setValor_Divisas_Dolares(a.getValor_Divisas_Dolares());
        ordenp.setValor_Divisas_Euros(a.getValor_Divisas_Euros());
        em.merge(ordenp);
        
    }
    
    @Override
    public void insertarIngreso(Ingreso a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Ingreso orden=new Ingreso();
        orden.setId(a.getId());
        orden.setBeneficiario(a.getBeneficiario());
        orden.setDescripcion(a.getDescripcion());
        orden.setEgreso_Dolares(a.getEgreso_Dolares());
        orden.setEgreso_Euros(a.getEgreso_Euros());
        orden.setEgreso_Lempiras(a.getEgreso_Lempiras());
        orden.setFecha(a.getFecha());
        orden.setIdNuestro(a.getIdNuestro());
        orden.setIngreso_Dolares(a.getIngreso_Dolares());
        orden.setIngreso_Euros(a.getIngreso_Euros());
        orden.setIngreso_Lempiras(a.getIngreso_Lempiras());
        orden.setIngresos(a.getIngresos());
        orden.setOrdenpago(a.getOrdenpago());
        orden.setProcedencia(a.getProcedencia());
        orden.setValor_Divisas_Dolares(a.getValor_Divisas_Dolares());
        orden.setValor_Divisas_Euros(a.getValor_Divisas_Euros());
        //hacer tratamiento excepcion
        String query="SELECT NSOCIO FROM SOCIOS WHERE NSOCIONUESTRO="+a.getSocio().getNSocioNuestro();
        List<String>Consulta=ConsultarID(query);
        Socios socio=new Socios();
        if(Consulta.isEmpty()){
            socio=new Socios();
        }else{
            for(String b:Consulta){
                Socios s=em.find(Socios.class, Long.parseLong(b));
                socio=s;
            }
        }
        String query2="SELECT CODIGO FROM BENEFICIARIOS WHERE CODIGONUESTRO="+a.getBeneficiario().getCodigoNuestro();
        List<String>Consulta2=ConsultarID(query2);
        Beneficiarios ben=new Beneficiarios();
        if(Consulta2.isEmpty()){
            ben=new Beneficiarios();
        }else{
            for(String b:Consulta2){
                Beneficiarios s=em.find(Beneficiarios.class, Long.parseLong(b));
                ben=s;
            }
        }
        String query3="SELECT CODIGO FROM PROYECTOS WHERE CODIGONUESTRO="+a.getIngresos().getCodigoNuestro();
        List<String>Consulta3=ConsultarID(query3);
        Proyectos ingres= new Proyectos();
        if(Consulta3.isEmpty()){
            ingres= new Proyectos();
        }else{
            for(String b:Consulta3){
                Proyectos s=em.find(Proyectos.class, Long.parseLong(b));
                ingres=s;
            }
        }
        String query4="SELECT NUM_ORDEN FROM ORDENPAGO WHERE NUM_ORDENNUESTRO="+a.getOrdenpago().getNum_OrdenNuestro();
        List<String>Consulta4=ConsultarID(query4);
        OrdenPago ordenpago=new OrdenPago();
        if(Consulta4.isEmpty()){
            ordenpago=new OrdenPago();
        }else{
            for(String b:Consulta4){
                OrdenPago s=em.find(OrdenPago.class, Long.parseLong(b));
                ordenpago=s;
            }
        }
        orden.setSocios(socio);
        orden.setBeneficiario(ben);
        orden.setIngresos(ingres);
        orden.setOrdenpago(ordenpago);
        System.out.println("Objeto creado: "+orden+" objeto insertado: "+a);
        em.persist(orden);
    }
    
    
    @Override
    public void eliminarIngreso(Ingreso a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Ingreso ord = em.find(Ingreso.class, a.getId());
        if(ord!=null){
            em.remove(ord);
        }
    
    }
    
    
    @Override
    public List<Ingreso> mostrarIngreso(){
        
        try {
            // TODO
            List<Ingreso> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM Ingreso";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Ingreso e=new Ingreso();
                e.setId(rs.getLong(1));
                e.setEgreso_Dolares(rs.getInt(2));
                e.setEgreso_Euros(rs.getInt(3));
                e.setEgreso_Lempiras(rs.getInt(4));
                e.setIngreso_Dolares(rs.getInt(5));
                e.setIngreso_Euros(rs.getInt(6));
                e.setIngreso_Lempiras(rs.getInt(7));
                e.setValor_Divisas_Dolares(rs.getInt(8));
                e.setValor_Divisas_Euros(rs.getInt(9));
                e.setIdNuestro(rs.getLong(10));
                e.setDescripcion(rs.getString(11));
                e.setFecha(rs.getDate(12));
                e.setProcedencia(rs.getString(13));
                String query="SELECT BENEFICIARIO_CODIGO,INGRESOS_CODIGO,SOCIO_NSOCIO,ORDENPAGO_NUM_ORDEN FROM INGRESO WHERE CODIGO_TRANSACCION="+e.getId();
                List<String>Consulta=ConsultarMas(query,4);
                OrdenPago ordenpago=new OrdenPago();
                Proyectos ingres= new Proyectos();
                Beneficiarios ben=new Beneficiarios();
                Socios socio=new Socios();
                System.out.print("Our consulto es"+Consulta.toString()+" CONSULTA EN 4 e "+Consulta.get(3));
                if(Consulta.isEmpty()){
                    /*ordenpago=new OrdenPago();
                    socio=new Socios();
                    ingres= new Proyectos();
                    ben=new Beneficiarios(); elevar exception*/
                }else{
                    OrdenPago s=em.find(OrdenPago.class, Long.parseLong(Consulta.get(3)));
                    ordenpago=s;
                    Proyectos d=em.find(Proyectos.class, Long.parseLong(Consulta.get(1)));
                    ingres=d;
                    Beneficiarios f=em.find(Beneficiarios.class, Long.parseLong(Consulta.get(0)));
                    ben=f;
                    Socios y=em.find(Socios.class, Long.parseLong(Consulta.get(2)));
                    socio=y;
                }
                e.setBeneficiario(ben);
                e.setIngresos(ingres);
                e.setSocios(socio);
                e.setOrdenpago(ordenpago);
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
//FIN ingreso
    
    //INICIO PROYECTOS
    
    
    @Override
    public void modificarProyectos(Proyectos a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Proyectos p = em.find(Proyectos.class, a.getCodigo());
        p.setCodigoNuestro(a.getCodigoNuestro());
        p.setNombre(a.getNombre());
        p.setEnUso(a.getEnUso());
        p.setCombustible(a.getCombustible());
        p.setMantenimiento(a.getMantenimiento());
        p.setContenedor(a.getContenedor());
        p.setDescripcion(a.getDescripcion());
        p.setParticipan(a.getParticipan());
        p.setIngresos(a.getIngresos());
        p.setGastos(a.getGastos());
        em.merge(p);
        
    }
    
    @Override
    public void insertarProyectos(Proyectos a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Proyectos p=new Proyectos();
        p.setCodigo(a.getCodigo());
        p.setCodigoNuestro(a.getCodigoNuestro());
        p.setNombre(a.getNombre());
        p.setEnUso(a.getEnUso());
        p.setCombustible(a.getCombustible());
        p.setMantenimiento(a.getMantenimiento());
        p.setContenedor(a.getContenedor());
        p.setDescripcion(a.getDescripcion());
        System.out.println("Objeto creado: "+p+" objeto insertado: "+a);
        em.persist(p);
    }
    
    
    @Override
    public void eliminarProyectos(Proyectos a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Proyectos p = em.find(Proyectos.class, a.getCodigo());
        if(p!=null){
            em.remove(p);
        }
    
    }
    
    
    @Override
    public List<Proyectos> mostrarProyectos(){
        
        try {
            // TODO
            List<Proyectos> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM PROYECTOS";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Proyectos e=new Proyectos();
                e.setCodigo(rs.getLong(1));
                e.setCodigoNuestro(rs.getLong(2));
                e.setCombustible(rs.getInt(3));
                e.setContenedor(rs.getInt(4));
                e.setDescripcion(rs.getString(5));
                e.setEnUso(rs.getBoolean(6));
                e.setMantenimiento(rs.getInt(7));
                e.setNombre(rs.getString(8));
                String query="SELECT CODIGO FROM NINOSJOVENES WHERE PROYECTO_CODIGO="+e.getCodigo();
                List<String>Consulta=ConsultarID(query);
                List<NinosJovenes> ninos=new ArrayList();
                if(Consulta.isEmpty()){
                    ninos.add(new NinosJovenes());
                }else{
                    for(String b:Consulta){
                        System.out.println("SOY UNA BANDERA "+b+" el LONG DA "+Long.parseLong(b));
                        NinosJovenes s=em.find(NinosJovenes.class, Long.parseLong(b));
                        ninos.add(s);
                    }
                }
                String query2="SELECT CODIGO_TRANSACCION FROM INGRESO WHERE INGRESOS_CODIGO="+e.getCodigo();
                List<String>Consulta2=ConsultarID(query2);
                List<Ingreso> ingresos=new ArrayList();
                if(Consulta2.isEmpty()){
                    ingresos.add(new Ingreso());
                }else{
                    for(String b:Consulta2){
                        System.out.println("SOY UNA BANDERA "+b+" el LONG DA "+Long.parseLong(b));
                        Ingreso s=em.find(Ingreso.class, Long.parseLong(b));
                        ingresos.add(s);
                    }
                }
                String query3="SELECT ID FROM GASTOS WHERE GASTOS_CODIGO="+e.getCodigo();
                List<String>Consulta3=ConsultarID(query3);
                List<Gastos> gastos=new ArrayList();
                if(Consulta3.isEmpty()){
                    gastos.add(new Gastos());
                }else{
                    for(String b:Consulta3){
                        System.out.println("SOY UNA BANDERA "+b+" el LONG DA "+Long.parseLong(b));
                        Gastos s=em.find(Gastos.class, Long.parseLong(b));
                        gastos.add(s);
                    }
                }
                e.setGastos(gastos);
                e.setIngresos(ingresos);
                e.setParticipan(ninos);
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
  
    //FIN PROYECTOS
    
    //INICIO SOCIOS
    
    @Override
    public void modificarSocios(Socios a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Socios s = em.find(Socios.class, a.getNSocio());
        s.setNSocioNuestro(a.getNSocioNuestro());
        s.setNombre(a.getNombre());
        s.setApellidos(a.getApellidos());
        s.setDni(a.getDni());
        s.setEstado(a.getEstado());
        s.setSexo(a.getSexo());
        s.setGrado(a.getGrado());
        s.setDireccion(a.getDireccion());
        s.setPoblacion(a.getPoblacion());
        s.setCodigoPostal(a.getCodigoPostal());
        s.setProvincia(a.getProvincia());
        s.setTelefonoMovil(a.getTelefonoMovil());
        s.setProvincia(a.getProvincia());
        s.setTelefonoFijo(a.getTelefonoFijo());
        s.setEmail(a.getEmail());
        s.setRelacion(a.getRelacion());
        s.setCertificado(a.getCertificado());
        s.setSector(a.getSector());
        s.setFechaAlta(a.getFechaAlta());
        s.setFechaBaja(a.getFechaBaja());
        s.setObservaciones(a.getObservaciones());
        s.setApadrinados(a.getApadrinados());
        s.setIngreso(a.getIngreso());
        em.merge(s);
        
    }
    
    @Override
    public void insertarSocios(Socios a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Socios s=new Socios();
        s.setNSocio(a.getNSocio());
        s.setNSocioNuestro(a.getNSocioNuestro());
        s.setNombre(a.getNombre());
        s.setApellidos(a.getApellidos());
        s.setDni(a.getDni());
        s.setEstado(a.getEstado());
        s.setSexo(a.getSexo());
        s.setGrado(a.getGrado());
        s.setDireccion(a.getDireccion());
        s.setPoblacion(a.getPoblacion());
        s.setCodigoPostal(a.getCodigoPostal());
        s.setProvincia(a.getProvincia());
        s.setTelefonoMovil(a.getTelefonoMovil());
        s.setTelefonoFijo(a.getTelefonoFijo());
        s.setEmail(a.getEmail());
        s.setRelacion(a.getRelacion());
        s.setCertificado(a.getCertificado());
        s.setSector(a.getSector());
        s.setFechaAlta(a.getFechaAlta());
        s.setFechaBaja(a.getFechaBaja());
        s.setObservaciones(a.getObservaciones());
        s.setApadrinados(a.getApadrinados());
        s.setIngreso(a.getIngreso());
        System.out.println("Objeto creado: "+s+" objeto insertado: "+a);
        em.persist(s);
    }
    
    
    @Override
    public void eliminarSocios(Socios a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Socios s = em.find(Socios.class, a.getNSocio());
        if(s!=null){
            em.remove(s);
        }
    
    }
    
    
    @Override
    public List<Socios> mostrarSocios(){
        
        try {
            // TODO
            List<Socios> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM SOCIOS";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Socios e=new Socios();
                e.setNSocio(rs.getLong(1));
                e.setNSocioNuestro(rs.getLong(2));
                e.setApellidos(rs.getString(3));
                e.setCertificado(rs.getString(4));
                e.setCodigoPostal(rs.getString(5));
                e.setDireccion(rs.getString(6));
                e.setDni(rs.getString(7));
                e.setEmail(rs.getString(8));
                e.setEstado(rs.getString(9));
                e.setFechaAlta(rs.getDate(10));
                e.setFechaBaja(rs.getDate(11));
                e.setGrado(rs.getString(12));
                e.setNombre(rs.getString(13));
                e.setObservaciones(rs.getString(14));
                e.setPoblacion(rs.getString(15));
                e.setProvincia(rs.getString(16));
                e.setRelacion(rs.getString(17));
                e.setSector(rs.getString(18));
                e.setSexo(rs.getString(19));
                e.setTelefonoFijo(rs.getString(20));
                e.setTelefonoMovil(rs.getString(21));
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
    
    
    //FIN SOCIOS
    
    //INICIO NINOSJOVENES
    
    
    @Override
    public void modificarNinosJovenes(NinosJovenes a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        NinosJovenes ninosj = em.find(NinosJovenes.class, a.getCodigo());
        ninosj.setAgente(a.getAgente());
        ninosj.setApellidos(a.getApellidos());
        ninosj.setBecas(a.getBecas());
        ninosj.setCodigoNuestro(a.getCodigoNuestro());
        ninosj.setColonia(a.getColonia());
        ninosj.setEstado(a.getEstado());
        ninosj.setFechaAlta(a.getFechaAlta());
        ninosj.setFechaAltaProyecto(a.getFechaAltaProyecto());
        ninosj.setFechaEntrada(a.getFechaEntrada());
        ninosj.setFechaNacimiento(a.getFechaNacimiento());
        ninosj.setFechaSalidaAcoes(a.getFechaSalidaAcoes());
        ninosj.setFechaSalidaProyecto(a.getFechaSalidaProyecto());
        ninosj.setGrado(a.getGrado());
        ninosj.setIdSocio(a.getIdSocio());
        ninosj.setNombre(a.getNombre());
        ninosj.setNotas(a.getNotas());
        ninosj.setObservaciones(a.getObservaciones());
        ninosj.setPersonal(a.getPersonal());
        ninosj.setProyecto(a.getProyecto());
        ninosj.setSexo(a.getSexo());
        em.merge(ninosj);  
    }
    
    
    @Override
    public void insertarNinosJovenes(NinosJovenes a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        NinosJovenes ninosj=new NinosJovenes();
        ninosj.setCodigo(a.getCodigo());
        ninosj.setAgente(a.getAgente());
        ninosj.setApellidos(a.getApellidos());
        ninosj.setCodigoNuestro(a.getCodigoNuestro());
        ninosj.setColonia(a.getColonia());
        ninosj.setEstado(a.getEstado());
        ninosj.setFechaAlta(a.getFechaAlta());
        ninosj.setFechaAltaProyecto(a.getFechaAltaProyecto());
        ninosj.setFechaEntrada(a.getFechaEntrada());
        ninosj.setFechaNacimiento(a.getFechaNacimiento());
        ninosj.setFechaSalidaAcoes(a.getFechaSalidaAcoes());
        ninosj.setFechaSalidaProyecto(a.getFechaSalidaProyecto());
        ninosj.setGrado(a.getGrado());
        System.out.println("Banderasas "+a.getIdSocio());
        ninosj.setIdSocio(a.getIdSocio());
        ninosj.setNombre(a.getNombre());
        ninosj.setObservaciones(a.getObservaciones());
        ninosj.setProyecto(a.getProyecto());
        ninosj.setSexo(a.getSexo());
        System.out.println("Objeto creado: "+ninosj+" objeto insertado: "+a);
        em.persist(ninosj);
    }
    
    
    @Override
    public void eliminarNinosJovenes(NinosJovenes a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        NinosJovenes nino = em.find(NinosJovenes.class, a.getCodigo());
        if(nino!=null){
            em.remove(nino);
        }
    
    }
    
    
    @Override
    public List<NinosJovenes> mostrarNinosJovenes(){
        
        try {
            // TODO
            List<NinosJovenes> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM NinosJovenes";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                NinosJovenes e=new NinosJovenes();
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
  
//FIN NINOSJOVENES
    
//INICIO PERSONAL
    
    
    @Override
    public void modificarPersonal(Personal a)  throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Personal person = em.find(Personal.class, a.getId());
        person.setDni(a.getDni());
        person.setApellido(a.getApellido());
        person.setCargo(a.getCargo());
        person.setEncargados(a.getEncargados());
        person.setNombre(a.getNombre());
        person.setSalario(a.getSalario());
        em.merge(person);
        
    }
    
    @Override
    public void insertarPersonal(Personal a) throws FinalException{
        // TODO
        //compruebaLogin(c.getUsuario());
        Personal person=new Personal();
        person.setId(a.getId());
        person.setDni(a.getDni());
        person.setApellido(a.getApellido());
        person.setCargo(a.getCargo());
        //person.setEncargados(a.getEncargados());
        person.setNombre(a.getNombre());
        person.setSalario(a.getSalario());
        System.out.println("Objeto creado: "+person+" objeto insertado: "+a);
        em.persist(person);
    }
    
    
    @Override
    public void eliminarPersonal(Personal a) throws FinalException{
        // TODO
        //compruebaLogin(a.getUsuario());
        Personal person = em.find(Personal.class, a.getId());
        if(person!=null){
            em.remove(person);
        }
    
    }
    
    
    @Override
    public List<Personal> mostrarPersonal(){
        
        try {
            // TODO
            List<Personal> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            String consulta="SELECT * FROM PERSONAL";
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                Personal e = new Personal();
                e.setId(rs.getLong(1));
                e.setApellido(rs.getString(2));
                e.setCargo(rs.getString(3));
                e.setDni(rs.getString(4));
                e.setNombre(rs.getString(5));
                e.setSalario(rs.getLong(6));
                String query="SELECT ENCARGADOS_CODIGO FROM NINOSJOVENES_PERSONAL WHERE PERSONAL_ID="+e.getId();
                List<String>Consulta=ConsultarID(query);
                List<NinosJovenes> ninos=new ArrayList();
                if(Consulta.isEmpty()){
                    ninos.add(new NinosJovenes());
                }else{
                    for(String b:Consulta){
                        System.out.println("SOY UNA BANDERA "+b+" el LONG DA "+Long.parseLong(b));
                        NinosJovenes s=em.find(NinosJovenes.class, Long.parseLong(b));
                        ninos.add(s);
                    }
                }
                String query2="SELECT ID FROM INFORMES WHERE PERSONAL_ID="+e.getId();
                List<String>Consulta2=ConsultarID(query2);
                List<Informes> informes=new ArrayList();
                if(Consulta2.isEmpty()){
                    informes.add(new Informes());
                }else{
                    for(String b:Consulta2){
                        System.out.println("SOY UNA BANDERA "+b+" el LONG DA "+Long.parseLong(b));
                        Informes s=em.find(Informes.class, Long.parseLong(b));
                        informes.add(s);
                    }
                }
                e.setEncargados(ninos);
                e.setInformes(informes);
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
  
//FIN PERSONAL
    
    
    
        @Override
        public List<String> ConsultarID(String consulta){
        
        try {
            // TODO
            List<String> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                String e=rs.getString(1);
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
        
    @Override
        public List<String> ConsultarMas(String consulta,int nValores){
        
        try {
            // TODO
            List<String> resultado=new ArrayList<>();
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/sun-appserv-samples", "app", "app");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(consulta);
            while(rs.next()){
                for(int i=1;i<nValores+1;i++){
                    String e=rs.getString(i);
                    resultado.add(e);
                }
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
}
