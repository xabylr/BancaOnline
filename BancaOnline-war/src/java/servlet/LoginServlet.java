/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Dinero;
import sesion.ClienteFacade;

/**
 *
 * @author Abel
 */
@WebServlet (name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private ClienteFacade cf;

    
    
    
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
        response.setContentType("text/html;charset=UTF-8");
        
        int dni;
        String password = request.getParameter("password");
                     
        //Comprobación del formato de dni
        
        String strDni = request.getParameter("DNI");
        if(utilidades.Dni.validar(strDni)){ //también comprueba si es nulo
            dni = utilidades.Dni.obtenerNumero(strDni);   
            
            //Comprobar dni en la base de datos aquí
                      Cliente encontrado = cf.find(dni);
                      
             PrintWriter salida;
             salida = response.getWriter();
                      
           if(encontrado!=null){
               salida.println("Dni encontrado!");
               salida.println(encontrado.getNombre());
           }
            else {
           
             salida.println("Dni no encontrado en base de datos");
            
           }
            
            
        }else{ //Código DNI inválido
            RequestDispatcher rd;
            rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/errorLogin.html");
            rd.forward(request, response);
        }

             
//        request.setAttribute();
//
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/contactoEditar.jsp");
//
//        dispatcher.forward(request, response);
        
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
        processRequest(request, response);
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
