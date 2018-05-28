/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;


import entidad.Cliente;
import entidad.Empleado;
import entidad.Movimiento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Dinero;
import sesion.ClienteFacade;
import sesion.EmpleadoFacade;
import sesion.IbanCC;
import utilidades.MovimientoString;


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
    
    public String registrar(){
        String ruta = "";
        
        if(utilidades.Dni.validar(this.dni)){
            int dniNumero = utilidades.Dni.obtenerNumero(this.dni);  
             empleado = empleadoFacade.validarPassword(dniNumero, this.password); 
             
           if(empleado!=null){ 
               
               /* try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../empleado/index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
                }*/
               ruta ="/empleado/index.xhtml";
           }else{
               cliente = clienteFacade.validarPassword(dniNumero, this.password);
            if(cliente!=null){
                   /*try {
                       listaStringMovimientos();
                       FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml");
                   } catch (IOException ex) {
                       Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
                   }*/
                   ruta = "/usuario/index.html";
            }else{
                this.setError("Error en el login :");
                this.setMensajeError("DNI o contraseña incorrecto");
                this.setRutaError(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
                ruta = "/../errorLogin.html";
//                FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO HECHA AUN
            }
           }
        } else{//Código DNI inválido
            this.setError("Error en el login :");
            this.setMensajeError("DNI o contraseña incorrecto");
            this.setRutaError(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            
            ruta = "/../errorLogin.html";
//                FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO HECHA AUN
        }    
        return ruta;
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
