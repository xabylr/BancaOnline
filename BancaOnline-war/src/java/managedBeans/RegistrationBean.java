/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author MykexMP
 */
@Named(value = "registrationBean")
@Dependent
public class RegistrationBean implements Serializable{

    private String DNI="";
    private String password="";
    
    public RegistrationBean() {}
    
    public String getDNI() {
        return DNI;
    }

    public String getPassword() {
        return password;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      
    public String doRegister() {
        if(utilidades.Dni.validar(DNI)){
            return "sucess";
            //FacesContext.getCurrentInstance().getExternalContext().redirect("/usuario");
        }
        else{
            return "fail";
        }      
    }
}