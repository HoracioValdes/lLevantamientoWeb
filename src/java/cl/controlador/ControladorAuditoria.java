/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dao.DAOauditoria;
import cl.modelo.Encuestador;
import cl.modelo.Ingreso;
import cl.modelo.ValidacionRegistro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Horacio
 */
@WebServlet(name = "ControladorAuditoria", urlPatterns = {"/avance.do"})
public class ControladorAuditoria extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        //Se generan objetos para la implementación
        DAOauditoria dao = new DAOauditoria();
        Encuestador encuestador;
        ArrayList<String> listaRegistros;
        ArrayList<ValidacionRegistro> listaValidos = new ArrayList<>();
        ArrayList<ValidacionRegistro> listaInvalidos = new ArrayList<>();
        ArrayList<String> listaRutValidos = new ArrayList<>();
        ArrayList<String> listaRutInvalidos = new ArrayList<>();
        //Se identifica la petición realizada.
        String userPath = request.getServletPath();

        int id_ingreso;
        //Llenado de id de ingreso
        id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

        if (userPath.equals("/avance.do")) {
            //Se recuperan los datos del encuestador.
            encuestador = dao.identificarEncuestador(id_ingreso);

            //Se toma el listado de socios ingresados
            listaRegistros = dao.listarRegistros(id_ingreso);

            //Recorremos el array verificando los registros
            for (String r : listaRegistros) {
                String rut;

                //Cargo el nombre y el rut
                rut = dao.consultarTrabajadorDos(r);

                if (rut.length() > 0) {
                    listaRutValidos.add(rut);
                }else{
                    if(dao.verificarExistencia(r)){
                        listaRutInvalidos.add(r);
                    }
                }
            }
            //Cantidades de lista
            int validos = listaRutValidos.size();
            int invalidos = listaRutInvalidos.size();

            //Se envía información a jsp de salida.
            request.setAttribute("encuestador", encuestador);
            request.setAttribute("validos", validos);
            request.setAttribute("listaRutInvalidos", listaRutInvalidos);
            request.setAttribute("invalidos", invalidos);
            request.setAttribute("listaRutValidos", listaRutValidos);
            request.getRequestDispatcher("avance.jsp").forward(request, response);

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
