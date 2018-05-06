/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entidad.Cuentacorriente;
import modelo.Dinero;
import modelo.IBAN;

/**
 *
 * @author javier
 */
public class IbanCC{
    Cuentacorriente cc;

    public IbanCC(Cuentacorriente cc) {
     this.cc=cc;      
    }
    
    public String getIBAN(){
        String entidad = cc.getEntidad().toString();
        String oficina = cc.getOficina().toString();
        String ncuenta = cc.getId().toString();
        
        String ccc = entidad+oficina+IBAN.getControlCCC(entidad, oficina, ncuenta)+ncuenta;
        
        String iban = "ES"+IBAN.getControlIBAN("ES", ccc)+ccc;
        
       return iban;
    }

    @Override
    public String toString() {
        return getIBAN();
    }
    
    
    
}
