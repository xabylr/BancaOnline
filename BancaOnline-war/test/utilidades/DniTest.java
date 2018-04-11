
package utilidades;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author javier
 */
public class DniTest {
    
    public DniTest() {
    }

    @Test
    public void testObtenerLetra_int() {
        assertTrue('Q' == Dni.obtenerLetra(94549658) );
        assertTrue('G' == Dni.obtenerLetra(23680942) );
        assertTrue('V' == Dni.obtenerLetra(11614143) );
        assertTrue('J' == Dni.obtenerLetra(42989980) );
        assertTrue('S' == Dni.obtenerLetra(86682898) );
        
    }

    @Test
    public void testObtenerLetra_String() {
        assertTrue('Q' == Dni.obtenerLetra("94549658") );
        assertTrue('G' == Dni.obtenerLetra("23680942") );
        assertTrue('V' == Dni.obtenerLetra("11614143") );
        assertTrue('J' == Dni.obtenerLetra("42989980") );
        assertTrue('S' == Dni.obtenerLetra("86682898") );
    }

    @Test
    public void testObtenerDniCompleto() {
        assertEquals("94549658Q", Dni.obtenerDniCompleto(94549658) );
        assertEquals("23680942G", Dni.obtenerDniCompleto(23680942) );
        assertEquals("11614143V", Dni.obtenerDniCompleto(11614143) );
        assertEquals("42989980J", Dni.obtenerDniCompleto(42989980) );
        assertEquals("86682898S", Dni.obtenerDniCompleto(86682898) );
    }

    @Test
    public void testObtenerNumero() {
        assertEquals(94549658, Dni.obtenerNumero("94549658Q") );
        assertEquals(23680942, Dni.obtenerNumero("23680942G") );
        assertEquals(11614143, Dni.obtenerNumero("11614143V") );
        assertEquals(42989980, Dni.obtenerNumero("42989980J") );
        assertEquals(86682898, Dni.obtenerNumero("86682898S") );
        
    }

    @Test
    public void testValidar() {
        assertTrue(Dni.validar("94549658Q") );
        assertTrue(Dni.validar("23680942G") );
        assertTrue(Dni.validar("11614143V") );
        assertTrue(Dni.validar("42989980J") );
        assertTrue(Dni.validar("86682898S") );
        
                
        assertFalse(Dni.validar("94549658Z") );
        assertFalse(Dni.validar("23680942A") );
        assertFalse(Dni.validar("11614143X") );
        assertFalse(Dni.validar("42989980M") );
        assertFalse(Dni.validar("86682898E") );
        
        assertTrue(Dni.validar("00000000T") );
          
        assertFalse(Dni.validar("94549658") );
        assertFalse(Dni.validar("23680942GA") );
        assertFalse(Dni.validar("0.000000T") );
        assertFalse(Dni.validar("0,000000T") );
        assertFalse(Dni.validar("-0000000T") );
        assertFalse(Dni.validar("-0.00000T") );  
        assertFalse(Dni.validar("11614143-") );
        assertFalse(Dni.validar("429899800") );
        assertFalse(Dni.validar("86") );
        assertFalse(Dni.validar("-3") );
        assertFalse(Dni.validar("0.94549658Q") );
        assertFalse(Dni.validar("9.4549658Q") );
        assertFalse(Dni.validar("00000000") );
        assertFalse(Dni.validar("ABCDEFGHJ") );
        
        
        
        assertFalse(Dni.validar(null) );
        
        
        
    }
    
}
