/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import entidad.Movimiento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import modelo.Dinero;
import sesion.ClienteFacade;
import sesion.DineroCC;
import sesion.IbanCC;

/**
 *
 * @author FlanaPC
 */
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable{

    @EJB
    private ClienteFacade clienteFacade;

    protected entidad.Cliente cliente;
    List<MovimientoString> movimientos = new ArrayList<>();    
    
    /**
     * Creates a new instance of Cliente
     */
    public ClienteBean() {
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    
    class MovimientoString{
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
    }
    
//    @PostConstruct
//    public void init(){
//        List<Movimiento> movs = clienteFacade.getMovimientosFechaDesc(this.getCliente().getCuenta());
//        
//        for (Movimiento m : movs) {
//            String saldo = new Dinero(m.getCuantia().longValue(),
//                    m.getDecimales(), m.getDivisa()).toString();
//            String ibanRemitente;
//            ibanRemitente = m.getRemitente() == null ? "INGRESO" : new IbanCC(m.getRemitente()).getIBAN();
//            String ibanReceptor;
//            ibanReceptor = m.getReceptor() == null ? "RETIRADA" : new IbanCC(m.getReceptor()).getIBAN();
//            String concepto = m.getConcepto();
//            Date date = new Date(m.getFecha().longValue());
//            movimientos.add(new MovimientoString(m, saldo, ibanRemitente, ibanReceptor, concepto, date));
//        }
//    }
}
