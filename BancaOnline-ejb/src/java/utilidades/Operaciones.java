
package utilidades;

/**
 *
 * Pequeñas herramientas matemáticas para el dinero y otros usos
 */
public class Operaciones {
    /*
    https://codingforspeed.com/using-faster-integer-power-in-java/
    */
       public static long potencia(long bas, long exp) {
        long re = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                re *= bas;        
            }
            exp >>= 1;
            bas *= bas; 
        }
        return re;
    }
}
