/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import sesion.ClienteFacade;

/**
 *
 * @author Abel
 */
@Named(value = "registroBean")
@Dependent
public class RegistroBean {

    @EJB
    private ClienteFacade clienteFacade;
    protected Cliente cliente;
    private String dni;
    private String password;
    
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

    public String getPassword() {
        return password;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    }
    
    public Cliente establecerCliente(){
        cliente = clienteFacade.find(dni);
        return cliente;
    }
    
}
