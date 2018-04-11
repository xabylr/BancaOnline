
package modelo;


public class Cliente {
    private int DNI;
    private String nombre;
    private String apellidos;
    private CuentaCorriente cuenta;

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }
    
     public CuentaCorriente getCuenta() {
        return cuenta;
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


   

    
    
    
    
    
    
}
