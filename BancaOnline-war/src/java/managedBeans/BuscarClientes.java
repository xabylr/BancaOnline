/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;


/**
 *
 * @author Stefan
 */
@Named(value = "buscarClientes")
@javax.enterprise.context.RequestScoped
public class BuscarClientes {
    @EJB
    private ClienteFacade clienteFacade;
    
    protected List<Cliente> clientes;

    /**
     * Creates a new instance of BuscarUsuario
     */
    public BuscarClientes() {
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    @PostConstruct
    public void init(){
        clientes = clienteFacade.findAll();
    }
}
