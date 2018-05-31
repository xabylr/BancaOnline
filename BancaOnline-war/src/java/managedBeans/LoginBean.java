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
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Dinero;
import sesion.ClienteFacade;
import sesion.EmpleadoFacade;
import sesion.IbanCC;


@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

    @EJB
    private EmpleadoFacade empleadoFacade;

    @EJB
    private ClienteFacade clienteFacade;
    
    protected Cliente cliente;
    protected Empleado empleado;
    protected String dni;
    protected String password;
    
    private String error, mensajeError, rutaError;
    
    /**
     * Creates a new instance of RegistroBean
     */
    public LoginBean() {
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
    
    public String validarLogin(){     
        if(utilidades.Dni.validar(dni)){
            int dniNumero = utilidades.Dni.obtenerNumero(dni);  
             empleado = empleadoFacade.validarPassword(dniNumero, password); 
           if(empleado!=null){               
               //NO ESTA HECHO AÚN
           }else{
               cliente = clienteFacade.validarPassword(dniNumero, password);
            if(cliente!=null){
                return "usuario";
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
        return null;
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
    
    

    
}
