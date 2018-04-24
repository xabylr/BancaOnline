/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.math.BigInteger;
import java.text.ParseException;
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
    
    /*
    Valida cualquier IBAN ya parseado
    https://en.wikipedia.org/wiki/International_Bank_Account_Number
    
    (no verifica la longitud adecuada para cada país)
    */
    public static boolean validarIBAN(String iban){
        String pais = iban.substring(0, 4);
        String codPais = "";
        for(char c : pais.toCharArray()){
            codPais= codPais+(10 + (c-'A') );
        }
        
        iban = iban.substring(4)+codPais;
        BigInteger convertido = new BigInteger(iban);
      int modulo =  convertido.mod(new BigInteger("97")).intValue();
      
        return modulo==1;
    }
    
    
    public static boolean validarCCC(String codigo) {
        int [] entidad = Arrays.stream(codigo.substring(0, 4).split("")).mapToInt(i -> Integer.parseInt(i)).toArray();     
        entidad[0]*=4;
        entidad[1]*=8;
        entidad[2]*=5;
        entidad[3]*=10;
        int A = Arrays.stream(entidad).sum();
        
        int [] oficina = Arrays.stream(codigo.substring(4, 8).split("")).mapToInt(i -> Integer.parseInt(i)).toArray();   
            oficina[0]*=9;
            oficina[1]*=7;
            oficina[2]*=3;
            oficina[3]*=6; 
            int B = Arrays.stream(oficina).sum();

            int C = (A+B)%11;
        
        int ctrl1 = 11-C;
            if (ctrl1>9) ctrl1 = (ctrl1 == 10? 1 : 0);
        
        final int[] cifras = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
            int [] cuenta = Arrays.stream(codigo.substring(10, 20).split("")).mapToInt(Integer::parseInt).toArray();
            for (int i=0; i< cuenta.length; i++) cuenta[i]*=cifras[i];

            int D = Arrays.stream(cuenta).sum();
            
        int ctrl2 = 11-D;
            if (ctrl2>9) ctrl2 = (ctrl2 == 10? 1 : 0);
            
        int ctrReal = Integer.parseInt(codigo.substring(8, 10));
        
        int ctrGenerado = ctrl1*10+ctrl2;
            
     
        return ctrReal==ctrGenerado;
    }
    
    
    /*
    Valida que el IBAN parseado sea correcto, además de validar el CCC
    */
    public static boolean validarIBAN_ES(String iban){
        return validarIBAN(iban) && validarCCC(iban);
    }
    
    
    
    
    
   
    
}
