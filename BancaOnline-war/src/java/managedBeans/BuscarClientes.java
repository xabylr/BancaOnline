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
    protected String valor;
    protected String criterio;
    protected String [] criterios = {"--Choose Critery--","DNI", "Nombre", "Apellidos", "Entidad", "Oficina","Nº Cuenta"};
    /**
     * Creates a new instance of BuscarUsuario
     */
    public BuscarClientes() {
    }

    public String[] getCriterios() {
        return criterios;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public String searchByCriteria(){
        switch(criterio){
               case "DNI":
                   clientes = clienteFacade.BuscarPorDNI(valor);
                   break;
                case "Nombre":
                   clientes = clienteFacade.BuscarPorNombre(valor);
                   break;
                case "Apellidos":
                   clientes = clienteFacade.BuscarPorApellido(valor);
                   break;
                case "Entidad":
                   clientes = clienteFacade.BuscarPorEntidad(valor);
                   break;
                case "Oficina":
                   clientes = clienteFacade.BuscarPorOficina(valor);
                   break;
                case "Nº Cuenta":
                   clientes = clienteFacade.BuscarPorNumeroCC(valor);
                   break;
           } 
        return "index";
    }
    
    @PostConstruct
    public void init(){
        if(criterio == null || criterio.equals(criterios[0])){
            clientes = clienteFacade.findAll(); //inicialmente se muestran todos
        }else{
           searchByCriteria();
        }
        
    }
}
