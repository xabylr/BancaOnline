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
import javax.inject.Inject;
import modelo.Dinero;
import sesion.ClienteFacade;
import sesion.DineroCC;
import sesion.EmpleadoFacade;
import sesion.IbanCC;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    @Inject
    private ClienteBean clienteBean;
    
    @EJB
    private EmpleadoFacade empleadoFacade;    

    @EJB
    private ClienteFacade clienteFacade;

    protected Empleado empleado;
    protected String dni;
    protected String password;

    private String error, mensajeError, rutaError;

    /**
     * Creates a new instance of RegistroBean
     */
    public LoginBean() {
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getDni() {
        return dni;
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
    
    public String validarLogin() {
        if (utilidades.Dni.validar(dni)) {
            int dniNumero = utilidades.Dni.obtenerNumero(dni);
            empleado = empleadoFacade.validarPassword(dniNumero, password);
            if (empleado != null) {
                //NO ESTA HECHO AÚN
            } else {
                clienteBean.setCliente(clienteFacade.validarPassword(dniNumero, password));
                if (clienteBean.getCliente() != null) {
                    return "cliente";
                } else {
                    this.setError("Error en el login :");
                    this.setMensajeError("DNI o contraseña incorrecto");
                    this.setRutaError(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
                    //FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO HECHA AUN
                }
            }
        } else {//Código DNI inválido
            this.setError("Error en el login :");
            this.setMensajeError("DNI o contraseña incorrecto");
            this.setRutaError(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            //FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO HECHA AUN
        }
        return null;
    }    
}
