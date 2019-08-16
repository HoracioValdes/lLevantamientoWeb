/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dao.DAOpuerto;
import cl.modelo.BuscarTrabajo;
import cl.modelo.ComboPuerto;
import cl.modelo.Trabajo;
import cl.modelo.TrabajoBuscado;
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
@WebServlet(name = "ControladorPuerto", urlPatterns = {"/datosPuerto.do", "/puertos.do", "/trabajoBuscado.do", "/datosPuertoEncotrado.do"})
public class ControladorPuerto extends HttpServlet {

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
        DAOpuerto dao = new DAOpuerto();
        ArrayList<ComboPuerto> listaPuerto;
        ArrayList<BuscarTrabajo> listaTrabajo;
        Trabajo trabajo;
        TrabajoBuscado trabajoBuscado;
        //Variables de mensajes
        String errores = "", msg = "";
        //Se identifica la petición realizada.
        String userPath = request.getServletPath();

        if (userPath.equals("/datosPuerto.do")) {
            //Se recuperan los registros de los comboBox.
            listaPuerto = dao.listarPuertos();
            request.setAttribute("listaPuerto", listaPuerto);
            request.getRequestDispatcher("trabajoPuerto.jsp").forward(request, response);

        } else if (userPath.equals("/puertos.do")) {
            //Se obtiene la variable de operación
            String operacion = String.valueOf(request.getParameter("action"));

            //Se ejecutan las acciones hacia la base de datos según la variable de operación
            if (operacion.equals("ingresar")) {
                trabajo = new Trabajo();

                int id_ingreso;
                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Llenado de variables y validación de datos para el objeto socio
                trabajo.setRut_socio(String.valueOf(request.getParameter("txtRut")).toUpperCase());
                if (trabajo.getRut_socio().isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (trabajo.getRut_socio().length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (trabajo.getRut_socio().length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(trabajo.getRut_socio())) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                boolean existencia = false;
                existencia = dao.existenciaPrevia(trabajo.getRut_socio());
                if (!existencia) {
                    errores += "El rut que ha tratado de ingresar no existe en el sistema. Primero deberá ingresar el trabajador con ese rut. <br />";
                }

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    trabajo.setId_puerto(Integer.parseInt(String.valueOf(request.getParameter("cboPuerto"))));
                }

                boolean confirmacionMeses = false;
                String[] itemsMeses = request.getParameterValues("cboMesesTrabajados");
                if (itemsMeses == null) {
                    errores += "Debes seleccionar al menos un mes de trabajo. <br />";
                } else {
                    for (int loopIndex = 0; loopIndex < itemsMeses.length; loopIndex++) {
                        if (itemsMeses[loopIndex].equals("ENERO")) {
                            trabajo.setEnero(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("FEBRERO")) {
                            trabajo.setFebrero(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("MARZO")) {
                            trabajo.setMarzo(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("ABRIL")) {
                            trabajo.setAbril(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("MAYO")) {
                            trabajo.setMayo(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("JUNIO")) {
                            trabajo.setJunio(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("JULIO")) {
                            trabajo.setJulio(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("AGOSTO")) {
                            trabajo.setAgosto(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("SEPTIEMBRE")) {
                            trabajo.setSeptiembre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("OCTUBRE")) {
                            trabajo.setOctubre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("NOVIEMBRE")) {
                            trabajo.setNoviembre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("DICIEMBRE")) {
                            trabajo.setDiciembre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        }
                    }
                }

                String comprobadorTurnos = String.valueOf(request.getParameter("txtTurnos"));
                if (comprobadorTurnos.isEmpty()) {
                    errores += "Debes ingresar los turnos realizados en el último mes. <br />";
                } else {
                    if (!numeroEntero(comprobadorTurnos)) {
                        errores += "Debes ingresar un valor numérico en los turnos realizados en el último mes. <br />";
                    } else {
                        trabajo.setTurnos(Integer.parseInt(comprobadorTurnos));
                    }
                }

                trabajo.setSeccion(String.valueOf(request.getParameter("cboSeccion")));
                if (trabajo.getSeccion().equals("null")) {
                    errores += "Debes seleccionar una sección. <br />";
                }

                String comprobadorFuncion = String.valueOf(request.getParameter("optFuncion"));
                if (comprobadorFuncion.equals("null")) {
                    errores += "Debes seleccionar la función ejercida. <br />";
                } else {
                    trabajo.setFuncion(String.valueOf(request.getParameter("optFuncion")));
                }

                String comprobadorAccidente = String.valueOf(request.getParameter("optAccidente"));
                if (comprobadorAccidente.equals("null")) {
                    errores += "Debes indicar si sufriste o no algún accidente. <br />";
                } else {
                    trabajo.setAccidente(Integer.parseInt(String.valueOf(request.getParameter("optAccidente"))));
                }

                //Consulta de mas_puerto
                boolean mas_puerto = dao.consultaMasPuerto(trabajo.getRut_socio());
                //Consulta de número de puertos
                int numero_puertos = dao.consultarNumeroPuertos(trabajo.getRut_socio());

                if (mas_puerto == false && numero_puertos == 1) {
                    errores += "Ya tiene un registro de trabajo portuario. No puede ingresar más porque ha declarado trabajar en un solo puerto.<br />";
                }
                
                //Existencia previa de Trabajo
                if (dao.existenciaPreviaTrabajo(trabajo.getId_puerto(), trabajo.getRut_socio())) {
                    errores += "El registro de trabajo que ha tratado de ingresar ya existe en el sistema.<br />";
                }

                if (errores.isEmpty()) {
                    

                    int cantFilas = dao.registrarTrabajo(trabajo);

                    //Verificar la inserción y enviar mensajes.
                    if (cantFilas > 0) {
                        dao.ingresarAuditoria(id_ingreso, "INGRESAR", "TRABAJA", trabajo.getId_puerto(), trabajo.getRut_socio());
                        msg = "Trabajo ingresado exitosamente";
                    } else {
                        msg = "Error en el ingreso";
                    }

                    request.setAttribute("msg", msg);
                } else {
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosPuerto.do").forward(request, response);

            } else if (operacion.equals("modificar")) {
                int id_ingreso;
                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                trabajo = new Trabajo();

                //Llenado de variables y validación de datos para el objeto socio
                trabajo.setRut_socio(String.valueOf(request.getParameter("txtRut")).toUpperCase());
                if (trabajo.getRut_socio().isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (trabajo.getRut_socio().length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (trabajo.getRut_socio().length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(trabajo.getRut_socio())) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                boolean existencia = false;
                existencia = dao.existenciaPrevia(trabajo.getRut_socio());
                if (!existencia) {
                    errores += "El rut que ha tratado de ingresar no existe en el sistema. Primero deberá ingresar el trabajador con ese rut. <br />";
                }

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    trabajo.setId_puerto(Integer.parseInt(String.valueOf(request.getParameter("cboPuerto"))));
                }

                boolean confirmacionMeses = false;
                String[] itemsMeses = request.getParameterValues("cboMesesTrabajados");
                if (itemsMeses == null) {
                    errores += "Debes seleccionar al menos un mes de trabajo. <br />";
                } else {
                    for (int loopIndex = 0; loopIndex < itemsMeses.length; loopIndex++) {
                        if (itemsMeses[loopIndex].equals("ENERO")) {
                            trabajo.setEnero(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("FEBRERO")) {
                            trabajo.setFebrero(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("MARZO")) {
                            trabajo.setMarzo(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("ABRIL")) {
                            trabajo.setAbril(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("MAYO")) {
                            trabajo.setMayo(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("JUNIO")) {
                            trabajo.setJunio(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("JULIO")) {
                            trabajo.setJulio(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("AGOSTO")) {
                            trabajo.setAgosto(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("SEPTIEMBRE")) {
                            trabajo.setSeptiembre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("OCTUBRE")) {
                            trabajo.setOctubre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("NOVIEMBRE")) {
                            trabajo.setNoviembre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        } else if (itemsMeses[loopIndex].equals("DICIEMBRE")) {
                            trabajo.setDiciembre(1);
                            if (confirmacionMeses == false) {
                                confirmacionMeses = true;
                            }
                        }
                    }
                }
                
                String comprobadorTurnos = String.valueOf(request.getParameter("txtTurnos"));
                if (comprobadorTurnos.isEmpty()) {
                    errores += "Debes ingresar los turnos realizados en el último mes. <br />";
                } else {
                    if (!numeroEntero(comprobadorTurnos)) {
                        errores += "Debes ingresar un valor numérico en los turnos realizados en el último mes. <br />";
                    } else {
                        trabajo.setTurnos(Integer.parseInt(comprobadorTurnos));
                    }
                }

                trabajo.setSeccion(String.valueOf(request.getParameter("cboSeccion")));
                if (trabajo.getSeccion().equals("null")) {
                    errores += "Debes seleccionar una sección. <br />";
                }

                String comprobadorFuncion = String.valueOf(request.getParameter("optFuncion"));
                if (comprobadorFuncion.equals("null")) {
                    errores += "Debes seleccionar la función ejercida. <br />";
                } else {
                    trabajo.setFuncion(String.valueOf(request.getParameter("optFuncion")));
                }

                String comprobadorAccidente = String.valueOf(request.getParameter("optAccidente"));
                if (comprobadorAccidente.equals("null")) {
                    errores += "Debes indicar si sufriste o no algún accidente. <br />";
                } else {
                    trabajo.setAccidente(Integer.parseInt(String.valueOf(request.getParameter("optAccidente"))));
                }
                
                //Existencia previa de Trabajo
                if (!dao.existenciaPreviaTrabajo(trabajo.getId_puerto(), trabajo.getRut_socio())) {
                    errores += "El registro de trabajo que ha tratado de modificar no existe en el sistema.<br />";
                }

                if (errores.isEmpty()) {

                    int cantFilas = dao.modificarTrabajo(trabajo);

                    //Verificar la inserción y enviar mensajes.
                    if (cantFilas > 0) {
                        dao.ingresarAuditoria(id_ingreso, "MODIFICAR", "TRABAJA", trabajo.getId_puerto(), trabajo.getRut_socio());
                        msg = "Trabajo modificado exitosamente";
                    } else {
                        msg = "Error en la modificación";
                    }
                    request.setAttribute("msg", msg);
                } else {
                    errores += "<br/><b>Deberá volver a buscar el registro de trabajo en puerto e introducir los valores de modificación correctamente.</b> <br />";
                    request.setAttribute("msg", errores);
                }

                request.getRequestDispatcher("datosPuerto.do").forward(request, response);

            } else if (operacion.equals("eliminar")) {

                int id_ingreso;
                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Llenado de variables y validación de datos para la eliminación
                String rut_socio = String.valueOf(request.getParameter("txtRut")).toUpperCase();
                if (rut_socio.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut_socio.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut_socio.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut_socio)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                boolean existencia = false;
                existencia = dao.existenciaPrevia(rut_socio);
                if (!existencia) {
                    errores += "El rut que ha tratado de ingresar no existe en el sistema. Primero deberá ingresar el trabajador con ese rut. <br />";
                }

                String comprobacionIdPuerto = String.valueOf(request.getParameter("cboPuerto"));
                int id_puerto = 0;
                if (comprobacionIdPuerto.equals("null")) {
                    errores += "Debes seleccionar un puerto. <br />";
                } else {
                    id_puerto = Integer.parseInt(String.valueOf(request.getParameter("cboPuerto")));
                }
                
                //Existencia previa de Trabajo
                if (!dao.existenciaPreviaTrabajo(id_puerto, rut_socio)) {
                    errores += "El registro de trabajo que ha tratado de eliminar no existe en el sistema.<br />";
                }

                if (errores.isEmpty()) {

                    boolean confirmacion = dao.eliminarTrabajo(rut_socio, id_puerto);

                    //Verificar la inserción y enviar mensajes.
                    if (confirmacion) {
                        dao.ingresarAuditoria(id_ingreso, "ELIMINAR", "TRABAJA", id_puerto, rut_socio);
                        msg = "Trabajo eliminado exitosamente";
                    } else {
                        msg = "Error en la eliminación";
                    }
                    request.setAttribute("msg", msg);
                } else {
                    errores += "<br/><b>Deberá volver a buscar el registro de trabajo en puerto.</b> <br />";
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosPuerto.do").forward(request, response);

            } else if (operacion.equals("buscar")) {
                //Llenado de variables y validación de datos para el objeto socio
                String rut_socio = String.valueOf(request.getParameter("txtRut")).toUpperCase();
                if (rut_socio.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut_socio.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut_socio.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut_socio)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                boolean existencia = false;
                existencia = dao.existenciaPrevia(rut_socio);
                if (!existencia) {
                    errores += "El rut que ha tratado de ingresar no existe en el sistema. Primero deberá ingresar el trabajador con ese rut. <br />";
                }

                if (existencia) {
                    //Consulta de número de puertos
                    int numero_puertos = dao.consultarNumeroPuertos(rut_socio);
                    if (numero_puertos < 1) {
                        errores += "El trabajador no cuenta con registros de trabajo en puerto. <br />";
                    }
                }

                if (errores.isEmpty()) {
                    //Se recuperan los registros de los comboBox.
                    listaTrabajo = dao.listarTrabajos(rut_socio);

                    request.setAttribute("listaTrabajo", listaTrabajo);
                    //Se retorna el mensaje de vuelta al jsp.
                    request.getRequestDispatcher("trabajosIngresados.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", errores);
                    //Se retorna el mensaje de vuelta al jsp.
                    request.getRequestDispatcher("datosPuerto.do").forward(request, response);
                }
            }
        } else if (userPath.equals("/trabajoBuscado.do")) {
            //Se obtienen las variables de idnetificación del registro
            int id_puerto = Integer.parseInt(String.valueOf(request.getParameter("id_puerto")));
            String rut_socio = String.valueOf(request.getParameter("rut_socio"));

            //Se recupera el registro específico
            trabajoBuscado = dao.buscarTrabajo(id_puerto, rut_socio);

            request.setAttribute("trabajoBuscado", trabajoBuscado);
            //Se retorna el mensaje de vuelta al jsp.
            request.getRequestDispatcher("datosPuertoEncotrado.do").forward(request, response);

        } else if (userPath.equals("/datosPuertoEncotrado.do")) {
            //Se recuperan los registros de los comboBox.
            listaPuerto = dao.listarPuertos();
            trabajoBuscado = (TrabajoBuscado) request.getAttribute("trabajoBuscado");

            request.setAttribute("listaPuerto", listaPuerto);
            request.setAttribute("trabajoBuscado", trabajoBuscado);
            request.getRequestDispatcher("trabajoPuertoEncontrado.jsp").forward(request, response);
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
        } catch (NumberFormatException e) {
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
