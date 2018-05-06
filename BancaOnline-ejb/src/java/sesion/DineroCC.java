/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entidad.Cuentacorriente;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import modelo.Dinero;

/**
 * Clase que extiende el comportamiento de dinero para 
 * buscar dinero de una cuenta corriente y actualizarlo
 *
 * @author javier
 * 
 */
public class DineroCC extends Dinero{

    CuentacorrienteFacade ccf = lookupCuentacorrienteFacadeBean();
    private Cuentacorriente cc;


    public DineroCC(Cuentacorriente cc) {
        super(cc.getSaldo().longValueExact() , cc.getDecimales(), cc.getDivisa());    
        this.cc = cc;

    }
    
    public void actualizarCuenta(){
        cc.setSaldo(BigInteger.valueOf(getLong()));
        cc.setDecimales(getDecimales());
        cc.setDivisa(getDivisa());
        
        ccf.edit(cc);
    }

    private CuentacorrienteFacade lookupCuentacorrienteFacadeBean() {
        try {
            Context c = new InitialContext();
            return (CuentacorrienteFacade) c.lookup("java:global/BancaOnline/BancaOnline-ejb/CuentacorrienteFacade!sesion.CuentacorrienteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}
