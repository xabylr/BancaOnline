/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entidad.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author javier
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "BancaOnline-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
       public Cliente  validarPassword(int dni, String pass){
       Cliente  resultado = null;
       Cliente c = find(dni);
       if(c!=null && c.getPassword().equals(pass) ) resultado=c;
       
       return resultado;
    }
       
       public Cuentacorriente obtenerCuenta(int dni){
           Cuentacorriente cuenta = null;
           List<Object> consulta = em.createQuery("Select c.cuenta from Cliente c where c.dni = :dni").setParameter("dni", dni).getResultList();
           if(!consulta.isEmpty()){
               for(Object o: consulta){
               cuenta = (Cuentacorriente)o;
               }
           }
           return cuenta;
       }
       
       public Collection<Movimientorealizado> movimientosRealizados(Cuentacorriente c){
           Collection<Movimientorealizado> resultado = new ArrayList<>();
           List<Object> consulta = em.createQuery("Select m.id from Movimientorealizado m where m.remitente = :codigoCuenta").setParameter("codigoCuenta", c).getResultList();
           if(!consulta.isEmpty()){
           for(Object o : consulta){
               resultado.add(new Movimientorealizado((long)o));
           }
           }
           return resultado;
       }
    
}
