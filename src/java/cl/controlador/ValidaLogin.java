/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dao.DAOportuarios;
import cl.modelo.Ingreso;
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
 * @author HVALDÉS
 */
@WebServlet(name = "ValidaLogin", urlPatterns = {"/login.do"})
public class ValidaLogin extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ValidaLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ValidaLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        //Se recupera la lista desde la base de datos.
        DAOportuarios dao = new DAOportuarios();
        ArrayList<Ingreso> lstIngreso = dao.listarIngreso();
       
        String errores="", msg="";
        
        //Se recuperan los parámetros desde la petición.
        String usuario = request.getParameter("txtUsuario");
        String pass = request.getParameter("txtPass");
        //Se realiza una validación básica. (No se aceptan vacíos)
        if (usuario.isEmpty()){
            errores += "Debes ingresar el usuario. <br />";
        }
        if (pass.isEmpty()){
            errores += "Debes ingresar la clave. <br />";
        }
        //Verificar si hubo errores.
        if (errores.isEmpty()){//Están los datos buscar el usuario
            //Se verifica si el usuario es válido.
            Ingreso ingresoValido = null;
            for (Ingreso i : lstIngreso) {
                if (i.getUsuario().equals(usuario) && i.getClave().equals(pass)){
                    ingresoValido = i;
                    break;
                }
            }
            //Verirficar si se da acceso al usuario
            if (ingresoValido != null){//Usuario autorizado
                //Se genera una sesión para el usuario.
                request.getSession().setAttribute("ingreso", ingresoValido);
                //Se le da acceso al formulario socio de la aplicación.
                request.getRequestDispatcher("datosSocio.do").forward(request, response);   
            } else {
               errores = "Usuario y/o Clave inválidas."; 
               request.setAttribute("msg", errores);
               request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else { //Devolver la petición al formulario.
            request.setAttribute("msg", errores);
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
