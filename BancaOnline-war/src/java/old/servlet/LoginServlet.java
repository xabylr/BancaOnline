/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.*;
import java.util.Collection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sesion.ClienteFacade;
import sesion.EmpleadoFacade;

/**
 *
 * @author Abel y Javier
 */
@WebServlet (name="LoginServlet", urlPatterns={"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @EJB
    private ClienteFacade cf;
    @EJB
    private EmpleadoFacade ef;

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
        
        RequestDispatcher rd;
        PrintWriter salida;
        salida = response.getWriter();
        
        HttpSession session=request.getSession();
        
             
        int dni;
        String password = request.getParameter("password");
        //Comprobación del formato de dni
        
        String strDni = request.getParameter("DNI");
        if(utilidades.Dni.validar(strDni)){ //también comprueba si es nulo
            dni = utilidades.Dni.obtenerNumero(strDni);   
            
            //Comprobar dni en la base de datos aquí
                     
             
             
             Empleado e = ef.validarPassword(dni, password);          
           if(e!=null){               
               session.setAttribute("empleado", e);
               response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/empleado"));
           }
           else{
            Cliente c = cf.validarPassword(dni, password);
            if(c!=null){
                session = request.getSession();
                session.setAttribute("cliente", c);
                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/usuario"));
            }else{
                request.setAttribute("terror", "Error en el login :(");
                request.setAttribute("error", "DNI o contraseña incorrecto");
                request.setAttribute("rerror", response.encodeRedirectURL(request.getContextPath() + "/login/"));
            
                rd = (RequestDispatcher)this.getServletContext().getRequestDispatcher("/avisos/error.jsp");
                rd.forward(request, response);
            }
          
           }
            
        }else{ //Código DNI inválido
            
            request.setAttribute("terror", "Error en el login :(");
            request.setAttribute("error", "DNI o contraseña incorrecto");
            request.setAttribute("rerror", response.encodeRedirectURL(request.getContextPath() + "/login/"));
            
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
