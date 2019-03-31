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
 * @author tualf
 */
@Entity
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_transaccion;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(length=30,nullable = false)
    private String procedencia;
    private Integer Ingreso_Euros,Ingreso_Lempiras,Ingreso_Dolares,Egreso_Euros,Egreso_Lempiras,Egreso_Dolares,Valor_Divisas_Euros,Valor_Divisas_Dolares,Total_Lempira;
    @Column(length = 500)
    private String descripcion;

    public Long getId() {
        return codigo_transaccion;
    }

    public void setId(Long codigo_transaccion) {
        this.codigo_transaccion = codigo_transaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo_transaccion != null ? codigo_transaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.codigo_transaccion == null && other.codigo_transaccion != null) || (this.codigo_transaccion != null && !this.codigo_transaccion.equals(other.codigo_transaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Ingreso[ codigo_transaccion=" + codigo_transaccion + " ]";
    }
    
}
