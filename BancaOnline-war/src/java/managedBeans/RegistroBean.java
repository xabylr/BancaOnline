/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import entidad.Empleado;
import entidad.Movimiento;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Dinero;
import sesion.ClienteFacade;
import sesion.EmpleadoFacade;
import sesion.IbanCC;

/**
 *
 * @author Abel
 */
@Named(value = "registroBean")
@RequestScoped
public class RegistroBean implements Serializable{

    @EJB
    private EmpleadoFacade empleadoFacade;

    @EJB
    private ClienteFacade clienteFacade;
    
    protected Cliente cliente;
    protected Empleado empleado;
    protected String dni;
    protected String password;
    protected List<MovimientoString> movimientos;
    
    private String error, mensajeError, rutaError;
    
    /**
     * Creates a new instance of RegistroBean
     */
    public RegistroBean() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDni() {
        return dni;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getError() {
        return error;
    }

    public List<MovimientoString> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoString> movimientos) {
        this.movimientos = movimientos;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public String getRutaError() {
        return rutaError;
    }

    public String getPassword() {
        return password;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public void setRutaError(String rutaError) {
        this.rutaError = rutaError;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void registrar(){
        System.out.println("Java Siempremente malo " + dni);
        
        if(utilidades.Dni.validar(this.dni)){
            int dniNumero = utilidades.Dni.obtenerNumero(this.dni);  
             empleado = empleadoFacade.validarPassword(dniNumero, this.password); 
             System.out.println("Java malo");
           if(empleado!=null){               
               //FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO ESTA HECHO AUN
           }else{
               System.out.println("Java siempre malo");
               cliente = clienteFacade.validarPassword(dniNumero, this.password);
            if(cliente!=null){
                   try {
                       listaStringMovimientos();
                       FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml");
                   } catch (IOException ex) {
                       Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }else{
                this.setError("Error en el login :");
                this.setMensajeError("DNI o contraseña incorrecto");
                this.setRutaError(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            
//                FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO HECHA AUN
            }
           }
        } else{//Código DNI inválido
            this.setError("Error en el login :");
                this.setMensajeError("DNI o contraseña incorrecto");
                this.setRutaError(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            
//                FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO HECHA AUN
        }
        
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
    
    public void listaStringMovimientos(){
        List<Movimiento> movs = clienteFacade.getMovimientosFechaDesc(this.getCliente().getCuenta());
        movimientos = new ArrayList<>(); // :c
        
        for(Movimiento m : movs){
                            String saldo = new Dinero(m.getCuantia().longValue(),
                                    m.getDecimales(), m.getDivisa()).toString();
                            String ibanRemitente;
                            ibanRemitente = m.getRemitente()==null? "INGRESO" : new IbanCC(m.getRemitente()).getIBAN();
                            String ibanReceptor;
                            ibanReceptor = m.getReceptor()==null? "RETIRADA" : new IbanCC(m.getReceptor()).getIBAN();
                            String concepto = m.getConcepto();
                            Date date = new Date(m.getFecha().longValue());
                            movimientos.add(new MovimientoString(m,saldo,ibanRemitente,ibanReceptor,concepto,date));
        } 
        
    }
    
}
