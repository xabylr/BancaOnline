/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author javier
 */
public class DineroTest {
    
    public DineroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    

    @Test
    public void moverDineroInternacional(){
        Dinero homer = new Dinero("50", "EUR");
        Dinero homero = new Dinero("0", "MXN");
        
        Dinero treintaEur = new Dinero("30", "EUR");
        Dinero retirado = homer.retirar(treintaEur);
  
        homero.ingresar( retirado );
        
        assertEquals("694 MXN", homero.toString());
    }
    
    
    @Test
    public void moverDineroInternacional2(){
        Dinero pepe = new Dinero("50", "EUR");
        Dinero john = new Dinero("10", "GBP");
        
        Dinero treintaEur = new Dinero("30", "EUR");
       
        try{
             Dinero.mover(pepe, treintaEur, john);
        }catch (Dinero.SaldoInsuficiente e){
            fail();
        } 
       
 
        assertEquals("36,32 GBP", john.toString());
    }
    

    /**
     * Test of mover method, of class Dinero.
     */
    @Test
    public void testMover() throws Exception {
        Dinero pepe = new Dinero("50", "EUR");
        Dinero juan = new Dinero("3", "EUR");
        
        Dinero.mover(pepe, new Dinero("20", "EUR"), juan);
        
        assertEquals("30,00 EUR", pepe.toString());
        assertEquals("23,00 EUR", juan.toString());
        
    }

    /**
     * Test of retirar method, of class Dinero.
     */
    @Test
    public void testRetirar() throws Exception {
        Dinero pepe = new Dinero("50", "EUR");
        pepe.retirar(new Dinero("20", "EUR"));
          
        assertEquals("30,00 EUR", pepe.toString());
    }

    /**
     * Test of ingresar method, of class Dinero.
     */
    @Test
    public void testIngresar() throws Exception {
        Dinero pepe = new Dinero("50", "EUR");
        pepe.ingresar(new Dinero("30", "EUR"));
          
        assertEquals( "80,00 EUR", pepe.toString());
    }
    
     @Test
    public void testIngresar2() throws Exception {
        Dinero pepe = new Dinero("50", "EUR");
        Dinero cantidad = new Dinero("30", "EUR");
        pepe.ingresar(cantidad);
          
        assertEquals( "0,00 EUR", cantidad.toString());
    }

    /**
     * Test of getDivisa method, of class Dinero.
     */
    @Test
    public void testGetDivisa() {
        Dinero dinero = new Dinero("10,00", "EUR");
        assertEquals("EUR" , dinero.getDivisa());
    }


    /**
     * Test of getEntero method, of class Dinero.
     */
    @Test
    public void testGetEntero() {
        Dinero dinero = new Dinero("895,71", "EUR");
        assertTrue(dinero.getEntero()== 895);
    }

        @Test
    public void testGetEntero2() {
        Dinero dinero = new Dinero("895", "EUR");
        assertTrue(dinero.getEntero()== 895);
    }
    
    /**
     * Test of getDecimal method, of class Dinero.
     */
    @Test
    public void testGetDecimal() {
         Dinero dinero = new Dinero("895,71", "EUR");
         assertTrue(dinero.getDecimal()== 71);
    }

        @Test
    public void testGetDecimal2() {
         Dinero dinero = new Dinero("895,01", "EUR");
         assertTrue(dinero.getDecimal()== 01);
    }
    
        @Test
    public void testGetDecimal3() {
         Dinero dinero = new Dinero("895,10", "EUR");
         assertTrue(dinero.getDecimal()== 10);
    }
    
        @Test
    public void testGetDecimal4() {
         Dinero dinero = new Dinero("895,1", "EUR");
         assertTrue(dinero.getDecimal()== 10);
    }
    
    /**
     * Test of getStringDivisa method, of class Dinero.
     */
    @Test
    public void testGetStringDivisa() {
        Dinero dinero = new Dinero("895,71", "EUR");
        assertEquals("EUR", dinero.getStringDivisa());

    }


    /**
     * Test of setCuantia method, of class Dinero.
     */
    @Test
    public void testSetCuantia_3args() {
        Dinero dinero = new Dinero("0,00", "EUR");
        dinero.setCuantia(12351, "EUR");
        
        assertEquals("123,51", dinero.getNumero());
 
    }

    /**
     * Test of setEuros method, of class Dinero.
     */
    @Test
    public void testSetEuros() {
        Dinero dinero = new Dinero("0,00", "EUR");
        dinero.setEuros(132, 05);
        assertEquals("132,05", dinero.getNumero());
    }

    /**
     * Test of setCuantia method, of class Dinero.
     */
    @Test
    public void testSetCuantia_String_DineroDivisa() {
         Dinero dinero = new Dinero("0,00", "EUR");
         dinero.setCuantia("123,51", "EUR");
        
        assertEquals("123,51", dinero.getNumero());
 
    }

    /**
     * Test of getNumero method, of class Dinero.
     */
    @Test
    public void testGetNumero() {
        Dinero dinero = new Dinero("895,71", "EUR");
        assertEquals("895,71", dinero.getNumero() );
        
    }

        @Test
    public void testGetNumero2() {
        Dinero dinero = new Dinero("895", "EUR");
        assertEquals("895,00", dinero.getNumero());
        
    }
    
    /**
     * Test of toString method, of class Dinero.
     */
    @Test
    public void testToString() {
        Dinero dinero = new Dinero("895,71", "EUR");
        assertEquals("895,71 EUR", dinero.toString());
    }

    /**
     * Test of clone method, of class Dinero.
     */
    @Test
    public void testClone() throws Exception {
        Dinero dinero = new Dinero("895,71", "EUR");
        assertEquals("895,71", dinero.getNumero());
        Dinero clon = (Dinero) dinero.clone();
        assertEquals("895,71", dinero.getNumero());
        dinero.setCuantia("00,00", "EUR");
        assertEquals("0,00", dinero.getNumero());
        assertEquals("895,71", clon.getNumero());

    }
    
}
