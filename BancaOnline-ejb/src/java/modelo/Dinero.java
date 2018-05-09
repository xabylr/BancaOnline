package modelo;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import utilidades.Operaciones;



class Divisa{
  
   private String codigo; //objeto que identifica a una divisa, como un enumerado
   private Divisa base; //moneda de referencia
   private int decimales; //cuántos decimales como máximo utiliza la moneda
   private double equivalencia; //a cuántas unidades de la base corresponde
   
   
    public Divisa(String codigo, Divisa base, int decimales, double equivalencia) {
        this.codigo = codigo;
        this.base = base;
        this.decimales = decimales;
        this.equivalencia = equivalencia;
    }
    /*
        Cuando la base es uno mismo
    */
    public Divisa(String codigo, int decimales) {
        this(codigo, null, decimales, 1);
        base = this;
    }
    
    
    /*
       Utilizado para búsquedas
    */
    public Divisa(String codigo) {
       this(codigo, -1);
    }
    
    
    public String getCodigo() {
        return codigo;
    }
    
    
    public Divisa getBase() {
        return base;
    }

    public int getDecimales() {
        return decimales;
    }
    

    public double getEquivalencia() {
        return equivalencia;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        if (!this.codigo.equals( ((Divisa)obj).getCodigo()  ) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    } 
  
}


public class Dinero implements Cloneable  { 
    public class SaldoInsuficiente extends RuntimeException {

        public SaldoInsuficiente(String err) {
            super(err);
        }
    }
 
   private final static Divisa BASE;
   
   private final static class DivisasHashMap extends HashMap<String, Divisa>{
       public void agregar(Divisa divisa) {
           super.put( divisa.getCodigo(), divisa );
       }   
   }
   private static final DivisasHashMap DIVISAS = new DivisasHashMap();
  
   static{  
    //divisas con base en el Euro
    BASE = new Divisa("EUR", 2);      
    
    //actualizado el 20 de marzo de 2018
    //El euro es nuestra base
    //No sería demasiado difícil obtener las divisas actualizadas de Internet
    DIVISAS.agregar(BASE);
    DIVISAS.agregar(new Divisa("USD", BASE, 2 , 1.2316554527087  ));
    DIVISAS.agregar(new Divisa("GBP", BASE, 2 , 0.87747882028281 ));
    DIVISAS.agregar(new Divisa("MXN", BASE, 0 , 23.12128925759   ));
    DIVISAS.agregar(new Divisa("JPY", BASE, 0 , 130.48096027955  ));
    DIVISAS.agregar(new Divisa("BTC", BASE, 8 , 6852.17          ));
   
   } 
    
   
   private long cuantia; //número sin coma
   private Divisa divisa; //moneda a utilizar
  
   
   /*
     Convierte el Dinero a otra divisa, manteniendo el valor (utiliza strings)
     Posible optimización utilizando variable cuantía
     ajustando números con sus decimales

   */
   public void convertirA(String cod){
       convertirA(DIVISAS.get(cod));
   } 
   private void convertirA(Divisa div){
       
       //Si es la misma divisa, ya hemos terminado
       if(!this.divisa.equals(div)){    
           
           //tenemos que pasar el dinero a double (pérdida de precisión)         
           double cantidad = this.getDouble();
           
           //Convertimos nuestro dinero a la moneda base
           if(!this.divisa.equals(BASE)){
               cantidad = cantidad/divisa.getEquivalencia();
           }
           
           //Convertimos a moneda de destino
           cantidad = cantidad*div.getEquivalencia();

           //Guardamos nuestro dinero convertido
           this.setCuantia(cantidad, div);          
       }

   }
   /*
      Devuelve un Dinero con una copia de valor equivalente en otra divisa
      No se pierde el dinero original.
      Si la divisa es la misma se devuelve el mismo objeto
   */
   public Dinero equivalente(String cod){
       return equivalente(DIVISAS.get(cod));
   }
    private Dinero equivalente(Divisa div){
       Dinero devolver = this;
       //Si es la misma divisa, ya hemos terminado
       if(!this.divisa.equals(div)){
           devolver = (Dinero) devolver.clone();
           devolver.convertirA(div);
       }   
       return devolver;
    }
       
      /*
    -Mueve una cantidad de dinero especificada desde un remitente a un receptor
    El uso normal de esta función es el de realizar un movimiento pendiente
    -La cantidad a mover se borrará cuando se haya transferido
    -Si no se especifica remitente se considerará una operación de ingreso de efectivo
    -Si no se especifica receptor se considerará una operación de retirada de efectivo
    ej: mover(juan, new Dinero("30,00", Dinero.Divisa.Eur), pepito);
   
    Excepciones:
        -Si el remitente no tiene la cantidad suficiente (no se permiten deudas)
        -Si no se usan las mismas divisas y decimales (No está implementado)
    */
    public static void mover(Dinero remitente, Dinero cantidad, Dinero receptor) throws SaldoInsuficiente{
        
        if(remitente == null){
            receptor.ingresar(cantidad);
            
        }else if (receptor == null){
             remitente.retirar(cantidad);
             
        }else{
            receptor.ingresar(remitente.retirar(cantidad));
        }  
        
    }
    
    
      /*
    -Devuelve una cantidad de Dinero retirada
    El uso normal de esta función es el de realizar
    un movimiento pendiente o retirar dinero de un cajero
   
    Excepciones:
        -Si el remitente no tiene la cantidad suficiente (no se permiten deudas)
        -Si no se usan las mismas divisas y decimales (No está implementado)
    */
    public Dinero retirar(Dinero cantidad) throws SaldoInsuficiente{
       cantidad.convertirA( this.divisa );
       
       
        if(this.cuantia<cantidad.cuantia)
            throw new SaldoInsuficiente("No se permiten números rojos");

        this.cuantia-=cantidad.cuantia;

        
        return cantidad;
    }
    
     /*
    -Ingresa una cantidad de Dinero introducida
    El uso normal de esta función es el de realizar
    un movimiento pendiente o retirar dinero de un cajero
    -La cantidad a mover se borrará cuando se haya transferido
   
    Excepciones:
        -Si el remitente no tiene la cantidad suficiente (no se permiten deudas)
        -Si no se usan las mismas divisas y decimales (No está implementado)
    */
    public void ingresar(Dinero cantidad){
      /*  if(this.divisa!= cantidad.divisa | this.decimales != cantidad.decimales)
            throw new Exception("¡No usar, no implementado!");*/
        cantidad.convertirA(this.divisa.getCodigo());
      
        this.cuantia+=cantidad.cuantia;
        cantidad.cuantia=0; //borramos el dinero ya ingresado
    }
    
    //########################## v CONSTRUCTORES v #############################
   
    public Dinero(){
        this("0,00");
    }
    
    /*
        Dinero en euros
    */
    public Dinero(String cantidad) {
        this(cantidad, DIVISAS.get("EUR") ); //2 decimales por defecto
    }
    
    public Dinero(long cantidad, int decimales, String cod){
        Divisa encontrada = DIVISAS.get(cod);
        Divisa insertar = encontrada;
        
        //Comprobamos si el número de decimales se ajusta a la norma
        //De lo contrario creamos una moneda especial; ej: "EUR(3)"
        if(decimales != encontrada.getDecimales() ){
            insertar = new Divisa(encontrada.getCodigo()+"("+decimales+")",
                    encontrada.getBase(),
                    decimales, 
                    encontrada.getEquivalencia());
            
            DIVISAS.agregar(insertar);       
        }
        
        setCuantia(cantidad, insertar);
    }
    
    
    private Dinero(String cantidad, Divisa div) {
        divisa = div;
        setCuantia(cantidad, divisa);   
    }
    
    public Dinero(String cantidad, String cod) {
        this(cantidad, DIVISAS.get(cod));
    }
 
    
    //########################## ^ CONSTRUCTORES ^ #############################
   
    public String getDivisa(){
        return divisa.getCodigo();
    }
    
    
    /*
    Devuelve el número a la izquierda de la coma
    */
    public long getEntero(){
        return cuantia/Operaciones.potencia(10, divisa.getDecimales());
    }
    
    /*
    Devuelve el número a la derecha de la coma
    */
    public long getDecimal(){
        return cuantia%Operaciones.potencia(10, divisa.getDecimales());
    }
    
    public String getStringDivisa() {
        return divisa.getCodigo();
    }
   
      /*
      Indicar el número sin coma y el número de decimales a correr la coma
    ej: sinDecimales = 29190
        nDecimales=2
        div = Dinero.Divisa.EUR
    */
    private void setCuantia(long sinDecimales, Divisa div){
        cuantia = sinDecimales;
        divisa = div;
    }
    
    public void setCuantia(long sinDecimales, String cod){
        setCuantia(sinDecimales, DIVISAS.get(cod));
    }
    
    public void setEuros(long euros, long centimos){
        setCuantia(euros*100+centimos, DIVISAS.get("EUR"));
    }
            

    /*
      Introducir decimales con una coma
      Ej: cantidad = "291,90"
          div = Dinero.Divisa.EUR
    */
    public void setCuantia(String cantidad, String cod){
        setCuantia(cantidad, DIVISAS.get(cod));
    } 
    private void setCuantia(String cantidad, Divisa div){
        divisa = div;
        
        Scanner sc = new Scanner(cantidad);
        sc.useDelimiter(",");

        if(sc.hasNext()){
            String entero = sc.next();
            String decimal="";
            if(sc.hasNext()) decimal = sc.next();
            
            if(decimal.length()>divisa.getDecimales()) decimal = decimal.substring(0, divisa.getDecimales());
            while(decimal.length()<divisa.getDecimales()) decimal= decimal+"0";
            
            cuantia = Long.parseLong(entero+decimal);
        }
        
    }
    
    
    /*
        Establecer cantidad de forma imprecisa
    */
    public void setCuantia(double cantidad, String cod){
       setCuantia(cantidad, DIVISAS.get(cod));
    }
    private void setCuantia(double cantidad, Divisa div){     
       cantidad = cantidad*Operaciones.potencia(10, div.getDecimales() );
       setCuantia(Math.round(cantidad), div);
    }
    
    
    /*
        Devuelve el número con coma y sin divisa
        Ej: devuelve "30,00" o "512,37"
    */
    public String getNumero(){
        int decimales = divisa.getDecimales();
        String numero=null;
        if (decimales > 0) {
            StringBuilder buffer = null;
            if (cuantia < Operaciones.potencia(10, decimales)) {
                buffer = new StringBuilder(
                        String.format("%0" + (decimales + 1) + "d", cuantia));

            } else {
                buffer = new StringBuilder(String.valueOf(cuantia));
            }

            buffer.insert(buffer.length() - decimales, ',');
            numero = buffer.toString();

        }
        
           
        return numero;
    }
    
    /*
        Devuelve un double con la cantidad de dinero aproximada
    */
    public double getDouble(){
        double resultado= cuantia;
        int decimales = divisa.getDecimales();
        resultado /= Operaciones.potencia(10, decimales);
              
        return resultado;
    }
    
    /*
        Devuelve un long con el dinero guardado como decimal sin comas
        Utilizado en conjunción con getDecimales()
    */
    public long getLong(){
        return cuantia;
    }
    
    /*
        Devuelve el número de decimales a correr la coma desde la derecha
        Utilizado en conjunción con getLong()
    */
    public int getDecimales(){
        return divisa.getDecimales();
    }
    
    /*
        Devuelve un identificador en String de la divisa utilizada
        ej: "EUR"
    */
    public String getCodigoDivisa(){
        return divisa.getCodigo();
    }
    
    @Override
    public String toString() {
        return getNumero()+" "+getStringDivisa();
    }

    @Override
    protected Object clone(){
       try {
           super.clone();
       } catch (CloneNotSupportedException ex) {
           Logger.getLogger(Dinero.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        Dinero clonado = new Dinero();
        clonado.cuantia = this.cuantia;
        clonado.divisa = this.divisa;
        
        return clonado;
    }


    
}
