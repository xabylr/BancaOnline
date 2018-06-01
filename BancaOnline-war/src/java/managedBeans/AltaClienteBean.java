/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import entidad.Cliente;
import entidad.Cuentacorriente;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;
import sesion.IbanCC;

/**
 *
 * @author Jose Santos
 */
@Named(value = "altaClienteBean")
@RequestScoped
public class AltaClienteBean implements Serializable{

    @EJB
    private CuentacorrienteFacade cuentacorrienteFacade;

    @EJB
    private ClienteFacade clienteFacade;
    
    
    protected Cliente cliente;
    protected Cuentacorriente cuenta;

    
    protected String dni;
    protected String password;
    protected String password_again; 
    protected String nombre; 
    protected String apellidos;
    protected String entidad = "6969";
    protected String oficina = "6969";

    protected String terror, error, rutaerror;

    
    
    /**
     * Creates a new instance of AltaClienteBean
     */
    public AltaClienteBean() {
    }
    
    public Cuentacorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentacorriente cuenta) {
        this.cuenta = cuenta;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public CuentacorrienteFacade getCuentacorrienteFacade() {
        return cuentacorrienteFacade;
    }

    public void setCuentacorrienteFacade(CuentacorrienteFacade cuentacorrienteFacade) {
        this.cuentacorrienteFacade = cuentacorrienteFacade;
    }

    public ClienteFacade getClienteFacade() {
        return clienteFacade;
    }

    public void setClienteFacade(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_again() {
        return password_again;
    }

    public void setPassword_again(String password_again) {
        this.password_again = password_again;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }
    public String getTerror() {
        return terror;
    }

    public void setTerror(String terror) {
        this.terror = terror;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRutaerror() {
        return rutaerror;
    }

    public void setRutaerror(String rutaerror) {
        this.rutaerror = rutaerror;
    }
    
    public void guardar(){
        try{   

            if (! utilidades.Dni.validar(dni)) throw new IllegalArgumentException("DNI incorrecto");            
            int dniaux = utilidades.Dni.obtenerNumero(dni);

            
            if(!password.equals(password_again)) throw new IllegalArgumentException("Las contraseñas no coinciden");

            
            long numerocuenta = cuentacorrienteFacade.obtenerNumeroCC(
                    Short.parseShort(entidad), Short.parseShort(oficina));
            
            if(numerocuenta==-1) throw new IllegalArgumentException("Error al generar número de cuenta");
            
            Cuentacorriente cuenta = new Cuentacorriente();
            cuenta.setEntidad(Short.parseShort(entidad));
            cuenta.setOficina(Short.parseShort(oficina));
            cuenta.setCc(numerocuenta);
            cuenta.setSaldo(BigInteger.ZERO);
            cuenta.setDecimales(2);
            cuenta.setDivisa("EUR");
            
            Date tiempoActual = new Date();
            cuenta.setFechacreacion(BigInteger.valueOf(tiempoActual.getTime() / 1000L ) );
            
            cuentacorrienteFacade.create(cuenta);
            
        
            cliente = new Cliente(dniaux);
            cliente.setPassword(password);
            cliente.setNombre(nombre);
            cliente.setApellidos(apellidos);
            cliente.setCuenta(cuenta);
            
            clienteFacade.create(cliente);
            
        
        }catch (IllegalArgumentException e){
                /*request.setAttribute("terror", "Error en el formulario");
            request.setAttribute("error", "Motivo del error: "+e.getMessage());
            request.setAttribute("rerror", response.encodeRedirectURL(request.getContextPath() + "/empleado/altaCliente/"));
            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/avisos/error.jsp");
            rd.forward(request, response);*/
                
                this.setTerror("Error en el formulario");
                this.setError("Motivo del error: "+e.getMessage());
                this.setRutaerror(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
            
        }catch (Exception e) {
            
            /*request.setAttribute("terror", "Error no identificado");
            request.setAttribute("error", "Ha sucedido un error inesperado al dar de alta un nuevo cliente "
                    + ". Revisa los datos y vuelve a intentarlo. Excepción: "+e.getMessage());
            request.setAttribute("rerror", response.encodeRedirectURL(request.getContextPath() + "/empleado/altaCliente/"));
            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/avisos/error.jsp");
            rd.forward(request, response);*/
            
            this.setTerror("Error no identificado");
            this.setError("Ha sucedido un error inesperado al dar de alta un nuevo cliente");
            this.setRutaerror(FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath());
        }
        
    }
    
    @PostConstruct
    public void init () {
        /*this.listaSupermercados = this.microMarketFacade.findAll();
        this.listaDescuentos = this.discountCodeFacade.findAll();  
        if (this.customerBean.getIdCustomerSeleccionado() != -1) { // Editar
            this.cliente = this.customerFacade.find(this.customerBean.getIdCustomerSeleccionado());
            this.customerBean.setIdCustomerSeleccionado(-1);
            this.supermercadoSeleccionado = this.cliente.getZip().getZipCode();
            this.descuentoSeleccionado = this.cliente.getDiscountCode().getDiscountCode();
        } else { // Nuevo cliente
            cliente = new Customer();
        }*/
    }
}
