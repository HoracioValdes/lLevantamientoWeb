/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dao.DAOempresas;
import cl.modelo.ComboComuna;
import cl.modelo.ComboEmpresa;
import cl.modelo.ComboNacion;
import cl.modelo.ComboPuerto;
import cl.modelo.ComboSindicato;
import cl.modelo.EmpresaBuscada;
import cl.modelo.Socio;
import cl.modelo.SocioBuscado;
import java.io.IOException;
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
@WebServlet(name = "ControladorEmpresa", urlPatterns = {"/datosEmpresa.do", "/empresaBuscada.do", "/datosEmpresaEncotrada.do", "/empresa.do"})
public class ControladorEmpresa extends HttpServlet {

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
        //Corrección de caracteres
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //Se genera objetos para la implementación.
        DAOempresas dao = new DAOempresas();;
        ArrayList<ComboEmpresa> listaEmpresa;
        ArrayList<EmpresaBuscada> empresasBuscadas;
        ArrayList<ComboPuerto> listaPuerto;
        EmpresaBuscada empresaBuscada;
        //Variables de mensajes
        String errores = "", msg = "";
        //Se identifica la petición realizada.
        String userPath = request.getServletPath();

        if (userPath.equals("/datosEmpresa.do")) {
            //Se recuperan los registros de los comboBox.
            listaEmpresa = dao.listarEmpresas();
            listaPuerto = dao.listarPuertos();
            //Se envía información a jsp de salida.
            request.setAttribute("listaPuerto", listaPuerto);
            request.setAttribute("listaEmpresa", listaEmpresa);
            request.getRequestDispatcher("perteneceEmpresa.jsp").forward(request, response);

        } else if (userPath.equals("/empresa.do")) {
            //Se obtiene la variable de operación
            String operacion = String.valueOf(request.getParameter("action"));

            //Se ejecutan las acciones hacia la base de datos según la variable de operación
            if (operacion.equals("ingresar")) {

                String rut_empresa = null;
                String rut_socio;
                int id_puerto = 0;
                String tipo_contrato = null;

                int id_ingreso;
                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Llenado de variables y validación de datos para el objeto socio
                rut_socio = String.valueOf(request.getParameter("txtRut")).toUpperCase();
                if (rut_socio.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut_socio.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut_socio.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut_socio)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    id_puerto = Integer.parseInt(String.valueOf(request.getParameter("cboPuerto")));
                }

                String comprobacionTipoContrato = String.valueOf(request.getParameter("cboTipoContrato"));
                if (comprobacionTipoContrato.equals("null")) {
                    errores += "Debes seleccionar un tipo de contrato. <br />";
                } else {
                    tipo_contrato = String.valueOf(request.getParameter("cboTipoContrato"));
                }

                boolean existencia = false;
                existencia = dao.existenciaPrevia(rut_socio, id_puerto);
                if (!existencia) {
                    errores += "No existe un registro de trabajo en puerto que corresponda al rut y al puerto que ha tratado de ingresar. Primero deberá ingresar el trabajo en puerto con ese rut y puerto asociado. <br />";
                }

                String comprobadorRutEmpresa = String.valueOf(request.getParameter("cboEmpresa"));
                if (comprobadorRutEmpresa.equals("null")) {
                    errores += "Debes seleccionar la empresa en la cual se desempeña el trabajador. <br />";
                } else {
                    rut_empresa = String.valueOf(request.getParameter("cboEmpresa"));
                }
                
                //Consulta de existencia previa de resgistro pertenece
                if (dao.existenciaPreviaEmpresa(rut_socio, id_puerto, rut_empresa)) {
                    errores += "El registro que ha tratado de ingresar ya existe. <br />";
                }

                if (errores.isEmpty()) {

                    int cantFilas = dao.registrarPertenece(id_puerto, rut_socio, rut_empresa, tipo_contrato);

                    //Verificar la inserción y enviar mensajes.
                    if (cantFilas > 0) {
                        dao.ingresarAuditoria(id_ingreso, "INGRESAR", "PERTENECE", id_puerto, rut_socio, rut_empresa);
                        msg = "Relación con empresa ingresada exitosamente";
                    } else {
                        msg = "Error en el ingreso";
                    }

                    request.setAttribute("msg", msg);
                } else {
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosEmpresa.do").forward(request, response);

            } else if (operacion.equals("modificar")) {

                String rut_empresa = null;
                String rut_socio;
                int id_puerto = 0;
                String tipo_contrato = null;

                int id_ingreso;
                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Llenado de variables y validación de datos para el objeto socio
                rut_socio = String.valueOf(request.getParameter("txtRut")).toUpperCase();
                if (rut_socio.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut_socio.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut_socio.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut_socio)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    id_puerto = Integer.parseInt(String.valueOf(request.getParameter("cboPuerto")));
                }

                String comprobacionTipoContrato = String.valueOf(request.getParameter("cboTipoContrato"));
                if (comprobacionTipoContrato.equals("null")) {
                    errores += "Debes seleccionar un tipo de contrato. <br />";
                } else {
                    tipo_contrato = String.valueOf(request.getParameter("cboTipoContrato"));
                }

                String comprobadorRutEmpresa = String.valueOf(request.getParameter("cboEmpresa"));
                if (comprobadorRutEmpresa.equals("null")) {
                    errores += "Debes seleccionar la empresa en la cual se desempeña el trabajador. <br />";
                } else {
                    rut_empresa = String.valueOf(request.getParameter("cboEmpresa"));
                }
                
                //Consulta de existencia previa de resgistro pertenece
                if (!dao.existenciaPreviaEmpresa(rut_socio, id_puerto, rut_empresa)) {
                    errores += "El registro que ha tratado de modifcar no existe. <br />";
                }

                if (errores.isEmpty()) {

                    int cantFilas = dao.modificarPertenece(id_puerto, rut_socio, rut_empresa, tipo_contrato);

                    //Verificar la inserción y enviar mensajes.
                    if (cantFilas > 0) {
                        dao.ingresarAuditoria(id_ingreso, "MODIFICAR", "PERTENECE", id_puerto, rut_socio, rut_empresa);
                        msg = "Relación con empresa modificada exitosamente";
                    } else {
                        msg = "Error en la modificación";
                    }

                    request.setAttribute("msg", msg);
                } else {
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosEmpresa.do").forward(request, response);

            } else if (operacion.equals("eliminar")) {
                String rut_empresa = null;
                String rut_socio;
                int id_puerto = 0;

                int id_ingreso;

                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Validación y llenado de variable rut
                rut_socio = String.valueOf(request.getParameter("txtRut")).toUpperCase();

                if (rut_socio.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut_socio.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut_socio.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut_socio)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    id_puerto = Integer.parseInt(String.valueOf(request.getParameter("cboPuerto")));
                }

                String comprobadorRutEmpresa = String.valueOf(request.getParameter("cboEmpresa"));
                if (comprobadorRutEmpresa.equals("null")) {
                    errores += "Debes seleccionar la empresa en la cual se desempeña el trabajador. <br />";
                } else {
                    rut_empresa = String.valueOf(request.getParameter("cboEmpresa"));
                }
                
                //Consulta de existencia previa de resgistro pertenece
                if (!dao.existenciaPreviaEmpresa(rut_socio, id_puerto, rut_empresa)) {
                    errores += "El registro que ha tratado de eliminar no existe. <br />";
                }

                if (errores.isEmpty()) {

                    boolean confirmacion = dao.eliminarPerteneceEmpresa(id_puerto, rut_socio, rut_empresa);
                    
                    //Verificar la inserción y enviar mensajes.
                    if (confirmacion) {
                        dao.ingresarAuditoria(id_ingreso, "ELIMINAR", "PERTENECE", id_puerto, rut_socio, rut_empresa);
                        msg = "Relación con empresa eliminada exitosamente";
                    } else {
                        msg = "Error en la eliminación";
                    }
                    request.setAttribute("msg", msg);
                } else {
                    errores += "<br/><b>Deberá volver a buscar el registro de la empresa.</b> <br />";
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosEmpresa.do").forward(request, response);

            } else if (operacion.equals("buscar")) {

                //Llenado de variable rut
                String rut_socio = String.valueOf(request.getParameter("txtRut")).toUpperCase();
                //validación de rut ingresado
                if (rut_socio.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut_socio.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut_socio.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut_socio)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                int id_puerto = 0;

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    id_puerto = Integer.parseInt(String.valueOf(request.getParameter("cboPuerto")));
                }
                
                boolean existencia = false;
                existencia = dao.existenciaPrevia(rut_socio, id_puerto);
                if (!existencia) {
                    errores += "No existe un registro de trabajo en puerto que corresponda al rut y al puerto que ha tratado de ingresar. Primero deberá ingresar el trabajo en puerto con ese rut y puerto asociado. <br />";
                }

                if (existencia) {
                    //Consulta de número de puertos
                    int numero_empresas = dao.consultarNumeroEmpresas(rut_socio, id_puerto);
                    if (numero_empresas < 1) {
                        errores += "El trabajador no cuenta con registros de empresas asociadas en ese puerto. <br />";
                    }
                }

                if (errores.isEmpty()) {
                    //Se recuperan los registros de los comboBox.
                    empresasBuscadas = dao.listarEmpresasIngresadas(rut_socio, id_puerto);

                    request.setAttribute("empresasBuscadas", empresasBuscadas);
                    //Se retorna el mensaje de vuelta al jsp.
                    request.getRequestDispatcher("empresasIngresadas.jsp").forward(request, response);
                    
                } else {
                    request.setAttribute("msg", errores);
                    //Se retorna el mensaje de vuelta al jsp.
                    request.getRequestDispatcher("datosEmpresa.do").forward(request, response);
                }

            }

        } else if (userPath.equals("/empresaBuscada.do")) {
            //Se obtienen las variables de idnetificación del registro
            String rut_empresa = String.valueOf(request.getParameter("rut_empresa"));
            String rut_socio = String.valueOf(request.getParameter("rut_socio"));
            int id_puerto = Integer.parseInt(String.valueOf(request.getParameter("id_puerto")));

            //Se recupera el registro específico
            empresaBuscada = dao.buscarPertenece(rut_empresa, rut_socio, id_puerto);

            request.setAttribute("empresaBuscada", empresaBuscada);
            //Se retorna el mensaje de vuelta al jsp.
            request.getRequestDispatcher("datosEmpresaEncotrada.do").forward(request, response);

        } else if (userPath.equals("/datosEmpresaEncotrada.do")) {
            empresaBuscada = (EmpresaBuscada) request.getAttribute("empresaBuscada");
            request.setAttribute("empresaBuscada", empresaBuscada);
            request.getRequestDispatcher("perteneceEmpresaEncontrada.jsp").forward(request, response);
        }
    }

//Método de validación de rut
    public static boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    //Método de revisión de datos ingresados para completar números
    private boolean numeroEntero(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
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
