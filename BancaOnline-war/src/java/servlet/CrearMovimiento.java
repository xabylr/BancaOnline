/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entidad.Cliente;
import entidad.Cuentacorriente;
import entidad.Movimiento;
import entidad.Movimiento_;
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
import sesion.ClienteFacade;
import sesion.CuentacorrienteFacade;
import sesion.DineroCC;
import sesion.MovimientoFacade;

/**
 *
 * @author Stefan
 */
@WebServlet(name = "CrearMovimiento", urlPatterns = {"/CrearMovimiento"})
public class CrearMovimiento extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
                //DATOS CUENTA
        String entidad = request.getParameter("entidad");
        String oficina = request.getParameter("oficina");
        String nc = request.getParameter("nc");
        
        Cliente cliente = clienteFacade.find(Integer.parseInt(request.getParameter("idCliente")));
        Cuentacorriente ccRemitente = cliente.getCuenta();
        
       
        
        Cuentacorriente ccReceptor = cuentacorrienteFacade.obtenerCuentaConCCC(entidad,oficina,nc);
        
               //DATOS MOVIMIENTO
        String movimiento = request.getParameter("movimiento");
        String importe = request.getParameter("importe");
        String divisa = request.getParameter("divisa");
        String concepto = request.getParameter("concepto");
       
        Movimiento m = new Movimiento();
        m.setConcepto(concepto);
        m.setDivisa(divisa);
        m.setReceptor(ccReceptor);
        Date tiempoActual = new Date();
        m.setFecha(BigInteger.valueOf(tiempoActual.getTime() / 1000L ) );
        m.setRemitente(ccRemitente);
        m.setDecimales(2);
        m.setCuantia(BigInteger.valueOf(Long.parseLong(importe)));
        
        //Falta lo de  SetSaldoPrev y esas mierdas
        
        m.setSaldoRttPrev(ccRemitente.getSaldo());
        m.setSaldoRttPrevDec(ccRemitente.getDecimales());
        m.setSaldoRttPrevDiv(ccRemitente.getDivisa());
        
        m.setSaldoRcpPrev(ccReceptor.getSaldo());
        m.setSaldoRcpPrevDec(ccReceptor.getDecimales());
        m.setSaldoRcpPrevDiv(ccReceptor.getDivisa());
        
        Dinero d = new Dinero(Long.parseLong(importe), 2, divisa);
        
        DineroCC dinCC = new DineroCC(ccReceptor);
         try{
            if(movimiento.equals("ingresar")){

                    dinCC.ingresar(d);
            }else if(movimiento.equals("retirada")){
                dinCC.retirar(d);
            }
         }catch(Exception e){
                
        }
         
         movimientoFacade.create(m);
         
         response.sendRedirect(response.encodeRedirectURL(request.getContextPath() +"/datosCrearMovimiento?idCliente=" + cliente.getDni()));
         //FALTA REDIRECCIONAR (A CREAR MOVIMIENTO DE NUEVO SUPONGO)
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
