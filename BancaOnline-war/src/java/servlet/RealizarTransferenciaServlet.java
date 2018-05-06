/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.Cliente;
import entidad.Cuentacorriente;
import entidad.Movimientorealizado;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;
import sesion.MovimientorealizadoFacade;

/**
 *
 * @author Jose Santos
 */
@WebServlet(name = "RealizarTransferenciaServlet", urlPatterns = {"/RealizarTransferencia"})
public class RealizarTransferenciaServlet extends HttpServlet {

    @EJB
    private ClienteFacade clienteFacade;

    @EJB
    private MovimientorealizadoFacade movimientorealizadoFacade;

    @EJB
    private CuentacorrienteFacade cuentacorrienteFacade;
    
    

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
        
        System.out.println("TRANSFERENCIA");
        
        BigInteger importe = new BigInteger(request.getParameter("importe"));
        String divisa = request.getParameter("divisa");
        String concepto = request.getParameter("concepto");
        int cuenta = Integer.parseInt(request.getParameter("cuenta"));
        Date fecha = new Date();
            
        Cliente micliente = (Cliente)request.getSession().getAttribute("cliente");
        
        Cuentacorriente emisor = micliente.getCuenta();
        Cuentacorriente receptor = cuentacorrienteFacade.find(cuenta);
        
        CrearMovimiento(importe, divisa, concepto, fecha, emisor, receptor);
        RealizarTransferencia(importe, divisa, emisor, receptor);
        
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/realizartransferencia/"));
    }
    
    void CrearMovimiento(BigInteger importe, String divisa, String concepto, Date fecha, Cuentacorriente emisor, Cuentacorriente receptor){
        Movimientorealizado movimiento = new Movimientorealizado();
        
        movimiento.setConcepto(concepto);
        movimiento.setCuantia(importe.negate());
        movimiento.setReceptor(receptor);
        movimiento.setDecimales(2);
        movimiento.setSaldoRemitentePrevio(emisor.getSaldo());
        
        movimientorealizadoFacade.create(movimiento);
    }
    
    void RealizarTransferencia(BigInteger importe, String divisa, Cuentacorriente emisor, Cuentacorriente receptor){
        BigInteger resemisor = emisor.getSaldo().add(importe.negate());
        BigInteger resreceptor = receptor.getSaldo().add(importe);
        
        emisor.setSaldo(resemisor);
        receptor.setSaldo(resreceptor);
        
        cuentacorrienteFacade.edit(emisor);
        cuentacorrienteFacade.edit(receptor);
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
