/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.Cliente;
import entidad.Cuentacorriente;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;
import sesion.IbanCC;

/**
 *
 * @author Jose Santos
 */
@WebServlet(name = "AnadirUsuarioServlet", urlPatterns = {"/AnadirUsuario"})
public class AnadirUsuarioServlet extends HttpServlet {

    @EJB
    private CuentacorrienteFacade cuentacorrienteFacade;

    @EJB
    private ClienteFacade clienteFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd;
        
                    
            String[] parametros = {"dni", "password", "password_again",
                "nombre", "apellidos", "entidad", "oficina"};
            
           String strdni=null, password=null, password_again=null, nombre=null, apellidos=null,
               entidad=null, oficina=null;
            
            
           String [] variables = new String[7];
           
            Cuentacorriente cuenta=null;
            Cliente cliente=null;
            
        try{   
           
           for (int i=0; i<variables.length; i++){
               variables[i] = request.getParameter(parametros[i]);
               if (variables[i] == null) throw new IllegalArgumentException("Campo "+parametros[i]+" vacío"); 
           }
           
           //Muy feo, pero no hay tiempo para arreglarlo
           
           strdni=variables[0];
           password=variables[1];
           password_again=variables[2];
           nombre=variables[3];
           apellidos=variables[4];
           entidad=variables[5];
           oficina=variables[6];
           
            
            if (! utilidades.Dni.validar(strdni)) throw new IllegalArgumentException("DNI incorrecto");            
            int dni = utilidades.Dni.obtenerNumero(strdni);

            
            if(!password.equals(password_again)) throw new IllegalArgumentException("Las contraseñas no coinciden");

            
            long numerocuenta = cuentacorrienteFacade.obtenerNumeroCC(
                    Short.parseShort(entidad), Short.parseShort(oficina));
            
            if(numerocuenta==-1) throw new IllegalArgumentException("Error al generar número de cuenta");
            
            cuenta = new Cuentacorriente();
            cuenta.setEntidad(Short.parseShort(entidad));
            cuenta.setOficina(Short.parseShort(oficina));
            cuenta.setCc(numerocuenta);
            cuenta.setSaldo(BigInteger.ZERO);
            cuenta.setDecimales(2);
            cuenta.setDivisa("EUR");
            
            Date tiempoActual = new Date();
            cuenta.setFechacreacion(BigInteger.valueOf(tiempoActual.getTime() / 1000L ) );
            
            cuentacorrienteFacade.create(cuenta);
            
        
            cliente = new Cliente(dni);
            cliente.setPassword(password);
            cliente.setNombre(nombre);
            cliente.setApellidos(apellidos);
            cliente.setCuenta(cuenta);
            
            clienteFacade.create(cliente);
            
        
             
        request.setAttribute("aviso", "Operación completada exitosamente");
        request.setAttribute("detalles", "Cliente "+nombre+" "+apellidos+" con IBAN: "+new IbanCC(cuenta).getIBAN()  );
        request.setAttribute("url", "/BancaOnline/empleado");
        request.setAttribute("duracion", 5);
        
        this.getServletContext().getRequestDispatcher("/avisos/aviso_redireccion.jsp")
                .forward(request, response);
            
        
        }catch (IllegalArgumentException e){
                request.setAttribute("terror", "Error en el formulario");
            request.setAttribute("error", "Motivo del error: "+e.getMessage());
            request.setAttribute("rerror", response.encodeRedirectURL(request.getContextPath() + "/empleado/altaUsuario/"));
            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/avisos/error.jsp");
            rd.forward(request, response);
            
        }catch (Exception e) {
            
            request.setAttribute("terror", "Error no identificado");
            request.setAttribute("error", "Ha sucedido un error inesperado al dar de alta un nuevo usuario "
                    + ". Revisa los datos y vuelve a intentarlo. Excepción: "+e.getMessage());
            request.setAttribute("rerror", response.encodeRedirectURL(request.getContextPath() + "/empleado/altaUsuario/"));
            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/avisos/error.jsp");
            rd.forward(request, response);
        }
        
        
       

            
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //No hacemos nada para no añadir el usuario dos veces
       // processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
