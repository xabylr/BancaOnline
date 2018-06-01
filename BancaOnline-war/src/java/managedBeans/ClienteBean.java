/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import sesion.DineroCC;
import sesion.IbanCC;

/**
 *
 * @author FlanaPC
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable{

    protected entidad.Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Creates a new instance of Cliente
     */
    public ClienteBean() {
    }
    
    public String getNombreYApellidos(){
        return cliente.getNombre() + " " + cliente.getApellidos();
    }
    
    public IbanCC getIban(){
        return new IbanCC(cliente.getCuenta());
    }
    
    public DineroCC getSaldo(){
        return new DineroCC(cliente.getCuenta());
    }
    
    /*class MovimientoString{
        Movimiento mov;
        String saldo, ibanRemitente, ibanReceptor, concepto;
        Date date;
        
        public MovimientoString(Movimiento m, String s, String iRem, String iRec, String con, Date d){
            mov = m;
            saldo = s;
            ibanRemitente = iRem;
            ibanReceptor = iRec;
            concepto = con;
            date = d;
        }

        public Movimiento getMov() {
            return mov;
        }

        public String getSaldo() {
            return saldo;
        }

        public String getIbanRemitente() {
            return ibanRemitente;
        }

        public String getIbanReceptor() {
            return ibanReceptor;
        }

        public String getConcepto() {
            return concepto;
        }

        public Date getDate() {
            return date;
        }
        
        
        
    }*/
}
