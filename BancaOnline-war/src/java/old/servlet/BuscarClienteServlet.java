/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesion.ClienteFacade;

/**
 *
 * @author Jose Santos
 */
@WebServlet(name = "BuscarClienteServlet", urlPatterns = {"/empleado/BuscarClientes"})
public class BuscarClienteServlet extends HttpServlet {

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
        List<Cliente> lista=null;
        
        String contenidoBusqueda = request.getParameter("contenidobusqueda");
        int criterioBusqueda = -1;
        String criterio = request.getParameter("criteriobusqueda");
        if(criterio!=null)
            criterioBusqueda = Integer.parseInt(criterio);
        
        if (contenidoBusqueda != null) {
            switch (criterioBusqueda) {
                case 0:
                    lista = clienteFacade.BuscarPorDNI(contenidoBusqueda);
                    break;

                case 1:
                    lista = clienteFacade.BuscarPorNombre(contenidoBusqueda);
                    break;
                    
                case 2:
                    lista = clienteFacade.BuscarPorApellido(contenidoBusqueda);
                    break;
                case 3:
                    lista = clienteFacade.BuscarPorEntidad(contenidoBusqueda);
                    break;
                case 4:
                    lista = clienteFacade.BuscarPorOficina(contenidoBusqueda);
                    break; 
                    
                case 5:
                    lista = clienteFacade.BuscarPorNumeroCC(contenidoBusqueda);
                    break;

            }
        }else{
            lista = clienteFacade.findAll();
        }
        
        

        request.setAttribute("lista", lista);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/empleado/buscar_clientes/index.jsp");
        rd.forward(request, response);

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
