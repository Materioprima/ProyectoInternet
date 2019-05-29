/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesFinal;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author nico-
 */
@Entity
public class Relacion implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    Personal personal;
    @ManyToOne
    NinosJovenes nino;
    public Long getCodigo() 
    {
        return id;
    }

    public void setCodigo(Long id) 
    {
        this.id = id;
    }
    public Personal getPersonal() 
    {
        if(personal==null){
            personal=new Personal();
        }
        return personal;
    }

    public void setPersonal(Personal id) 
    {
        this.personal = id;
    }
    
    public NinosJovenes getNino() 
    {
        if(nino==null){
            nino=new NinosJovenes();
        }
        return nino;
    }

    public void setNino(NinosJovenes id) 
    {
        this.nino = id;
    }
    
    @Override
    public String toString() {
        return nino+" : "+personal;
    }
}
