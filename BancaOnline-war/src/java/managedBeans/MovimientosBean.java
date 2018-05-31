package managedBeans;

import entidad.Movimiento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import modelo.Dinero;
import sesion.IbanCC;


@Named(value = "movimientosBean")
@RequestScoped
public class MovimientosBean {

    /**
     * Creates a new instance of MovimientosBean
     */
    public MovimientosBean() {
    }
    
    
        
   /* 
    public List<Movimiento>listaStringMovimientos(){
        List<Movimiento> movs = clienteFacade.getMovimientosFechaDesc(this.getCliente().getCuenta());
        List<Movimiento> movimientos = new ArrayList<>(); // :c
        
        for(Movimiento m : movs){
                            String saldo = new Dinero(m.getCuantia().longValue(),
                                    m.getDecimales(), m.getDivisa()).toString();
                            String ibanRemitente;
                            ibanRemitente = m.getRemitente()==null? "INGRESO" : new IbanCC(m.getRemitente()).getIBAN();
                            String ibanReceptor;
                            ibanReceptor = m.getReceptor()==null? "RETIRADA" : new IbanCC(m.getReceptor()).getIBAN();
                            String concepto = m.getConcepto();
                            Date date = new Date(m.getFecha().longValue());
                            movimientos.add(new LoginBean.MovimientoString(m,saldo,ibanRemitente,ibanReceptor,concepto,date));
        } 
        return movimientos;
    }
*/
    
}
