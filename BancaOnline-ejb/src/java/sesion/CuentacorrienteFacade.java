/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entidad.Cuentacorriente;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author javier
 */
@Stateless
public class CuentacorrienteFacade extends AbstractFacade<Cuentacorriente> {

    @PersistenceContext(unitName = "BancaOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentacorrienteFacade() {
        super(Cuentacorriente.class);
    }
    
    public Cuentacorriente obtenerCuentaConCCC(String entidad, String oficina, String cc){
      Query q =  em.createQuery("SELECT c FROM Cuentacorriente c WHERE c.entidad = :entidad AND c.oficina= :oficina AND c.cc = :cc");
      q.setParameter("entidad", Short.parseShort(entidad));
      q.setParameter("oficina",  Short.parseShort(oficina));
      q.setParameter("cc", Long.parseLong(cc));
      Cuentacorriente cuentaCorriente =null;
      Collection<Cuentacorriente> encontrado = q.getResultList();
      if(encontrado.size()==1)cuentaCorriente=encontrado.iterator().next();

  
        return cuentaCorriente;
    }
    
    /*
    Busca un número vacío para asignar un número de cuenta a un cliente
    */
    public long obtenerNumeroCC(short entidad, short oficina){
      long resultado=-1;
       Query q = em.createNativeQuery("SELECT MAX(cc) FROM cuentacorriente "
               + "WHERE entidad= ?1 AND oficina= ?2");
       q.setParameter(1, entidad);
       q.setParameter(2, oficina);
       try{
         BigDecimal bd = (BigDecimal) q.getSingleResult();
         resultado = bd.longValue();
         resultado++;
         
       }catch (NoResultException e){
           
       }
       
        return resultado;        
    }
    
    
    
    
}
