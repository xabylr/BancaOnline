/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Stefan
 */
@Named(value = "empleadoBean")
@RequestScoped
public class EmpleadoBean {
    @Inject
    private RegistroBean registrationBean;

    /**
     * Creates a new instance of Empleado
     */
   protected String nombre;
   protected String apellidos;
   protected int dni;
    
    public EmpleadoBean() {
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

  @PostConstruct
    public void init(){
        entidad.Empleado ee = this.registrationBean.getEmpleado();
        this.setApellidos(ee.getApellidos());
        this.setDni(ee.getDni());
        this.setNombre(ee.getNombre());
    }
    
}
