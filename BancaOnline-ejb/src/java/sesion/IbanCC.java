/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entidad.Cuentacorriente;
import modelo.IBAN;

/**
 *
 * @author javier
 */
public class IbanCC{
    final Cuentacorriente cc;
    String iban=null;

    public IbanCC(Cuentacorriente cc) {
     this.cc=cc;      
    }

    public IbanCC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getIBAN(){
        if(iban!=null)return iban;
        int entidad = cc.getEntidad();
        int oficina = cc.getOficina();
        long ncc = cc.getCc();
        
        iban = IBAN.getIbanES(entidad, oficina, ncc);
       return iban;
    }

    @Override
    public String toString() {
        return getIBAN();
    }
    
    
    
}
