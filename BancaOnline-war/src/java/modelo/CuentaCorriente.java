
package modelo;

public class CuentaCorriente {
    private String IBAN;
    private Dinero saldo;
    private long fechaCreacion; //POSIX
    private Movimiento movimientosEnviados;
    private Movimiento movimientosRecibidos;

    public CuentaCorriente(String IBAN) {
        this.IBAN = IBAN;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
        this.movimientosEnviados = movimientosEnviados;
        this.movimientosRecibidos = movimientosRecibidos;
    }
    
    
    
}
