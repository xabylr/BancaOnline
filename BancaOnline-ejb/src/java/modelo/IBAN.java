/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author javier
 * Utilidad estática para validar códigos IBAN ESPAÑOLES,
 * leerlos de diferentes maneras y mostrarlos
 * 
 * https://www.iban.es/
 */
public class IBAN {
    
    private static int getControlCCC(String entidad, String oficina, String numeroCuenta){
        int ctrl1, ctrl2;
            
        int [] nEntidad = new int[entidad.length()];         
        for (int i=0; i<entidad.length(); i++){
            nEntidad[i]= Character.getNumericValue(entidad.charAt(i));
        }
        
        nEntidad[0]*=4;
        nEntidad[1]*=8;
        nEntidad[2]*=5;
        nEntidad[3]*=10;
        int A = Arrays.stream(nEntidad).sum();
        
        int [] nOficina = new int[oficina.length()];         
        for (int i=0; i<oficina.length(); i++){
            nEntidad[i]= Character.getNumericValue(oficina.charAt(i));
        }  
            nOficina[0]*=9;
            nOficina[1]*=7;
            nOficina[2]*=3;
            nOficina[3]*=6; 
            int B = Arrays.stream(nOficina).sum();

            int C = (A+B)%11;
        
        ctrl1 = 11-C;
            if (ctrl1>9) ctrl1 = (ctrl1 == 10? 1 : 0);
        
        final int[] cifras = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
        
        int [] cuenta = new int[numeroCuenta.length()];         
        for (int i=0; i<numeroCuenta.length(); i++){
            nEntidad[i]= Character.getNumericValue(numeroCuenta.charAt(i));
        }  
        
        for (int i=0; i< cuenta.length; i++) cuenta[i]*=cifras[i];

        int D = Arrays.stream(cuenta).sum();
            
        ctrl2 = 11-D;
            if (ctrl2>9) ctrl2 = (ctrl2 == 10? 1 : 0);
        
            return ctrl1+ctrl2; 
    }
    
    private static int getControlIBAN(String pais, String ccc){
        String codPais = "";
        for(char c : pais.toCharArray()){
            codPais= codPais+(10 + (c-'A') );
        }
        
        String aux = ccc+codPais+"00";     
        BigInteger convertido = new BigInteger(aux);
      int modulo =  convertido.mod(new BigInteger("97")).intValue();
      int control = 98-modulo;
      
        return control;
    }
    
    
    /*
    Valida y parsea un IBAN para dejarlo como una cadena consecutiva de números
    */
    static String parsear(String entrada) throws IllegalArgumentException{
        String pais;
        String codigo;
        
        entrada = entrada.toUpperCase();
        entrada = entrada.trim();
        Scanner sc = new Scanner(entrada);
        if(sc.hasNext("[A-Z]")){
            pais =  sc.next("[A-Z");
            if (pais.length()!= 2 || !sc.hasNext()) 
                throw new IllegalArgumentException("Código de país incorrecto");
          
            codigo = sc.next();
            codigo = codigo.replaceAll("\\D",""); //quitamos todo lo que no sea número
            
            if(!validarCCC(codigo) ) 
                throw new IllegalArgumentException("Verificación de IBAN incorrecta");
            
       
        }else throw new IllegalArgumentException("Formato de IBAN incorrecto");
        
        
        
        return pais+codigo;
    }

    /*
    tokeniza suponiendo que sea una dirección IBAN válida y parseada
    {c_País, dig_control, entidad, oficina, ctrl_bancario, n_cuenta}
    http://www.lasexta.com/tecnologia-tecnoxplora/ciencia/divulgacion/iban-asi-calculan-numeros-cuenta-bancaria_2014020957fca03d0cf2fd8cc6b0e1a2.html
    */
    public static String[] tokenizarIBAN_ES(String entrada){
        String[] salida = {entrada.substring(0, 2), entrada.substring(2, 4),
        entrada.substring(4, 8), entrada.substring(8, 12), entrada.substring(12, 14),
        entrada.substring(14, 24)  };
        
        return salida;
    }
    
    
    public int getCCCfromIBAN(String iban){
        String [] tok = tokenizarIBAN_ES(iban);
        return Integer.parseInt(tok[5]);
    }
    
     /*
    Valida cualquier CCC español ya parseado
    */
      public static boolean validarCCC(String codigo) {
        if (codigo.length()!= 20) return false;
        
        int cc =getControlCCC(codigo.substring(0, 4), codigo.substring(4, 8), codigo.substring(10));
        int ccActual = Integer.parseInt(codigo.substring(8, 10));
        
        return ccActual==cc;
    }
    
    
    /*
    Valida cualquier IBAN ya parseado
    https://en.wikipedia.org/wiki/International_Bank_Account_Number
    
    (no verifica la longitud adecuada para cada país)
    */
    public static boolean validarIBAN(String iban){
        
       int cc = getControlIBAN(iban.substring(0, 2), iban.substring(4)) ;
       
       int ccActual = Integer.parseInt(iban.substring(2, 4));
        
        return ccActual==cc;
    }
    
    
    public String getCCC(String entidad, String oficina, String numeroCuenta){
            return entidad+oficina+getControlCCC(entidad, oficina, numeroCuenta)+numeroCuenta;   
    }
    
    public String getIban(String pais, String ccc){
     return pais+getControlIBAN(pais, ccc);
    }
    
    
    /*
    Valida que el IBAN parseado sea correcto, además de validar el CCC
    */
    public static boolean validarIBAN_ES(String iban){
        if(iban.length()!= 24) return false;
        
        return validarIBAN(iban) && validarCCC(iban);
    }
  
    
}
