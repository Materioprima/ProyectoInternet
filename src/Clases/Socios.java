/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nico-
 */
@Entity
public class Socios implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NSocio;
    @Column(length=30,nullable = false)
    private String nombre,apellidos,dni;
    private String estado,sexo,grado,direccion,poblacion,codigoPostal,provincia,telefonoMovil,telefonoFijo,email,relacion,certificado,sector;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta,fechaBaja;
    @Column(length = 500)
    private String observaciones;
}
