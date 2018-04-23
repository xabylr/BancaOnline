
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase singleton
 */
public class Banco {
    private static Banco banco;
    
    private List<Empleado> empleados;
    private List<Cliente> clientes;
    private List <CuentaCorriente> cuentasCorrientes;
    private String nombre;
    
    private Banco(){
        empleados = new ArrayList<>();
        clientes = new ArrayList<>();
        nombre = "Banco UMA";
    }
    
    public static Banco getBanco(){
        if (banco==null) banco = new Banco();
        return banco;
    }
    
    
    
}
