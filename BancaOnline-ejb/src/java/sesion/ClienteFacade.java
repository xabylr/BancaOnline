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
import javax.persistence.Query;

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
       
       public Cuentacorriente getCuenta(int dni){
           Cuentacorriente cuenta = null;
           
           Query q = em.createNamedQuery("Cliente.findByDni");
           q.setParameter("dni", dni);
           Cliente c = (Cliente) q.getSingleResult();
           
           return c.getCuenta();
       }
       
       private List<Movimiento> getMovimientosOrdenados(Cuentacorriente c, String orden){
           List<Movimiento> resultado = new ArrayList<>();
           Query q = em.createQuery("SELECT m FROM Movimiento m WHERE m.remitente = :remitente ORDER BY m."+orden);
           q.setParameter("remitente", c);
           return q.getResultList();
       }
       
       public List<Movimiento> getMovimientosFechaDesc(Cuentacorriente c){
         return getMovimientosOrdenados(c, "fecha desc");
       }
       
       public List<Cliente> BuscarPorDNI(String dni){
           List<Cliente> resultado;
           Query q = em.createQuery("SELECT c FROM Cliente c WHERE FUNC( 'LPAD', c.dni, 8, '0' ) LIKE :patron");
           q.setParameter("patron", dni+"%");
           resultado = (List<Cliente>)q.getResultList();
           return resultado;
       }
       
       public List<Cliente> BuscarPorNombre(String nombre){
           List<Cliente> resultado;
           Query q = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :nombre");
           q.setParameter("nombre", "%"+nombre+"%");
           resultado = q.getResultList();
           return resultado;
       }
       
       public List<Cliente> BuscarPorApellido(String apellidos){
           List<Cliente> resultado;
           Query q = em.createQuery("SELECT c FROM Cliente c WHERE c.apellidos LIKE :apellidos");
           q.setParameter("apellidos", "%"+apellidos+"%");
           resultado = q.getResultList();
           return resultado;
       }
       
       public List<Cliente> BuscarPorEntidad(String entidad){
           List<Cliente> resultado;
           Query q = em.createQuery("SELECT c FROM Cliente c WHERE "
                   + "FUNC( 'LPAD', c.cuenta.entidad, 4, '0' ) LIKE :entidad" );
           q.setParameter("entidad", entidad+"%");
           resultado = q.getResultList();
           return resultado;
       }
       
              public List<Cliente> BuscarPorOficina(String oficina){
           List<Cliente> resultado;
               Query q = em.createQuery("SELECT c FROM Cliente c WHERE "
                   + "FUNC( 'LPAD', c.cuenta.oficina, 4, '0' ) LIKE :oficina" );
           q.setParameter("oficina", oficina+"%");
           resultado = q.getResultList();
           return resultado;
       }
              
              public List<Cliente> BuscarPorNumeroCC(String ncuenta){
           List<Cliente> resultado;
               Query q = em.createQuery("SELECT c FROM Cliente c WHERE "
                   + "FUNC( 'LPAD', c.cuenta.cc, 10, '0' ) LIKE :ncuenta" );
           q.setParameter("ncuenta", "%"+ncuenta+"%");
           resultado = q.getResultList();
           return resultado;
       }
              
              
    
}
