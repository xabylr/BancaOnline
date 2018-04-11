package utilidades;

/**
 * Utilidad estática para verificar un dni y para obtener y quitar la letra del final
 * http://www.interior.gob.es/web/servicios-al-ciudadano/dni/calculo-del-digito-de-control-del-nif-nie
 * 
 * @author Javier
 * 
 */
public class Dni {
    private Dni(){} //Esta clase no debe instanciarse
    static char[] tabla = {'T','R','W','A','G','M','Y','F','P','D','X','B',
                           'N','J','Z','S','Q','V','H','L','C','K','E'};
    
    
    public static char obtenerLetra(int dni){
        return tabla[dni%tabla.length];
    }
    
    public static char obtenerLetra(String dni){
        return obtenerLetra(Integer.parseInt(dni));
    }
    
    /*
     Utiliza el número para devolver un string del DNI con su letra
    */
    public static String obtenerDniCompleto(int dni){
      return ""+dni+obtenerLetra(dni);
    }
    
    /*
    Comprueba si el dni no tiene letra ya o se la quita, pero no valida
    */
    public static int obtenerNumero(String dni) throws NumberFormatException{
        if(dni.length()!= 9 )
            if(dni.length()==8) return Integer.parseInt(dni);
            else throw new NumberFormatException();
        
        return Integer.parseUnsignedInt(dni.substring(0,8));
        
    }
    
    
    
    public static boolean validar(String dni){ //Comprobar que la letra es correcta
        if (dni == null ) return false;
        
        boolean resultado = false;
        if(dni.length()== 9){
            try{
                 int numero = obtenerNumero(dni);
                 char letra = Character.toUpperCase(dni.charAt(8)) ;
                 if(obtenerLetra(numero) == letra) resultado = true; 
            
            }catch (NumberFormatException e){
                resultado = false;
            }
           
        }
        
        return resultado;
    }
    
}
