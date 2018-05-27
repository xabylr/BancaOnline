/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import entidad.Empleado;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import sesion.ClienteFacade;
import sesion.EmpleadoFacade;

/**
 *
 * @author Abel
 */
@Named(value = "registroBean")
@Dependent
public class RegistroBean implements Serializable{

    @EJB
    private EmpleadoFacade empleadoFacade;

    @EJB
    private ClienteFacade clienteFacade;
    
    protected Cliente cliente;
    protected Empleado empleado;
    private String dni;
    private String password;
    
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
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RegistroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(utilidades.Dni.validar(this.getDni())){
            int dniNumero = utilidades.Dni.obtenerNumero(this.getDni());  
             
             empleado = empleadoFacade.validarPassword(dniNumero, this.getPassword());          
           if(empleado!=null){               
//               FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/index.xhtml"); NO ESTA HECHO AUN
           }else{
               cliente = clienteFacade.validarPassword(dniNumero, this.getPassword());
            if(cliente!=null){
                   try {
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
    
}
