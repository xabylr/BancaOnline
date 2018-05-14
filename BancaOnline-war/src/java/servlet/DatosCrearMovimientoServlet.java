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
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.IBAN;
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;
import sesion.IbanCC;

/**
 *
 * @author Jose Santos
 */
@WebServlet(name = "CrearMovimientoServlet", urlPatterns = {"/datosCrearMovimiento"})
public class DatosCrearMovimientoServlet extends HttpServlet {

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
        
        int dniCliente = Integer.parseInt(request.getParameter("idCliente"));
        
        Cliente client = clienteFacade.find(dniCliente);
        Cuentacorriente cuenta = client.getCuenta();
        
        IbanCC iban = new IbanCC(cuenta);
        String ib = iban.getIBAN();
        
        String [] datos = IBAN.tokenizarIBAN_ES(ib);
        
        request.setAttribute("datos", datos);
        
        RequestDispatcher rd; 
        rd = (RequestDispatcher) this.getServletContext().getRequestDispatcher("/empleado/crearmovimiento/index.jsp");
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