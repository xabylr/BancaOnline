/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.Cliente;
import entidad.Cuentacorriente;
import entidad.Movimiento;
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
import modelo.Dinero;
import modelo.IBAN;
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;
import sesion.DineroCC;
import sesion.MovimientoFacade;

/**
 *
 * @author Jose Santos
 */
@WebServlet(name = "RealizarTransferenciaServlet", urlPatterns = {"/usuario/RealizarTransferencia"})
public class RealizarTransferenciaServlet extends HttpServlet {

    @EJB
    private ClienteFacade clienteFacade;

    @EJB
    private MovimientoFacade movimientoFacade;

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
        
        String ctrliban = request.getParameter("ctrliban");
        String entidad = request.getParameter("entidad");
        String oficina = request.getParameter("oficina");
        String ctrlCCC = request.getParameter("ctrlCCC");
        String nc = request.getParameter("nc");
        
       String iban = IBAN.parsear("ES"+ctrliban+entidad+oficina+ctrlCCC+nc);
       
        if(!IBAN.validarIBAN_ES(iban) ) throw new IllegalArgumentException("IBAN NO VÁLIDO");
       
        
        Cliente cliente = (Cliente)request.getSession().getAttribute("cliente");
       
        Cuentacorriente ccRemitente = cliente.getCuenta();
        
       
        
        Cuentacorriente ccReceptor = 
                cuentacorrienteFacade.obtenerCuentaConCCC(entidad,oficina,nc);
     
  
        String importe = request.getParameter("importe");
        String divisa = request.getParameter("divisa");
        
        Dinero cantidad = new Dinero(importe, divisa);
        
        DineroCC remitente = new DineroCC(ccRemitente);
        DineroCC receptor = new DineroCC(ccReceptor);

        
        Movimiento movimiento = new Movimiento();
        movimiento.setConcepto(request.getParameter("concepto"));
        movimiento.setCuantia(BigInteger.valueOf(cantidad.getLong()) );
        movimiento.setDecimales(cantidad.getDecimales());
        movimiento.setRemitente(ccRemitente);
        movimiento.setReceptor(ccReceptor);
        movimiento.setDivisa(divisa);
        
        movimiento.setSaldoRttPrev(ccRemitente.getSaldo());
        movimiento.setSaldoRttPrevDec(ccRemitente.getDecimales());
        movimiento.setSaldoRttPrevDiv(ccRemitente.getDivisa());
        
        movimiento.setSaldoRcpPrev(ccReceptor.getSaldo());
        movimiento.setSaldoRcpPrevDec(ccReceptor.getDecimales());
        movimiento.setSaldoRcpPrevDiv(ccReceptor.getDivisa());

        
        Date tiempoActual = new Date();
        movimiento.setFecha(BigInteger.valueOf(tiempoActual.getTime() / 1000L ) );
        
        movimientoFacade.create(movimiento);
        
        Dinero.mover(remitente, cantidad, receptor);

   
        request.setAttribute("aviso", "Operación completada exitosamente");
        request.setAttribute("detalles", "Tu saldo actual es de "+remitente.toString());
        request.setAttribute("url", "/BancaOnline/usuario");
        request.setAttribute("duracion", 5);
        
        this.getServletContext().getRequestDispatcher("/avisos/aviso_redireccion.jsp")
                .forward(request, response);
        
        //response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/realizartransferencia"));
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
        //NO HACER NADA, SOLO POST
        //processRequest(request, response);
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
        try{
             processRequest(request, response);
        }catch(IllegalArgumentException e){
             response.getWriter().print("Excepción:");
             response.getWriter().println(e.getMessage());
             response.getWriter().print("\n(TODO: cambiar la forma de mostrar este error)");
             
        }
       
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
