/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion;

import entidad.Cuentacorriente;
import modelo.Dinero;

/**
 *
 * @author javier
 */
public class DineroCC extends Dinero{

    public DineroCC(Cuentacorriente cc) {
        
        super(cc.getSaldo().longValueExact() , cc.getDecimales(), cc.getDivisa());
        
        
        
          
    }
    
}
