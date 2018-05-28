/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import entidad.Movimiento;
import java.util.Date;

/**
 *
 * @author Stefan
 */
public class MovimientoString {
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
        

