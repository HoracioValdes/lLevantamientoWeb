/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.controlador;

import cl.dao.DAOportuarios;
import cl.modelo.ComboComuna;
import cl.modelo.ComboEmpresa;
import cl.modelo.ComboNacion;
import cl.modelo.ComboSindicato;
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
@WebServlet(name = "ControladorSocio", urlPatterns = {"/datosSocio.do", "/socios.do", "/datosSocioBuscado.do"})
public class ControladorSocio extends HttpServlet {

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
        DAOportuarios dao = new DAOportuarios();
        ArrayList<ComboSindicato> listaSindicato;
        ArrayList<ComboComuna> listaComuna;
        ArrayList<ComboNacion> listaNacion;
        Socio socio;
        SocioBuscado socioBuscado;
        //Variables de mensajes
        String errores = "", msg = "";
        //Se identifica la petición realizada.
        String userPath = request.getServletPath();

        if (userPath.equals("/datosSocio.do")) {
            //Se recuperan los registros de los comboBox.
            listaSindicato = dao.listarSindicatos();
            listaComuna = dao.listarComunas();
            listaNacion = dao.listarNacion();
            //Se envía información a jsp de salida.
            request.setAttribute("listaSindicato", listaSindicato);
            request.setAttribute("listaComuna", listaComuna);
            request.setAttribute("listaNacion", listaNacion);
            request.getRequestDispatcher("socios.jsp").forward(request, response);

        } else if (userPath.equals("/socios.do")) {
            //Se obtiene la variable de operación
            String operacion = String.valueOf(request.getParameter("action"));

            //Se ejecutan las acciones hacia la base de datos según la variable de operación
            if (operacion.equals("ingresar")) {

                socio = new Socio();

                int id_ingreso;
                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Llenado de variables y validación de datos para el objeto socio
                socio.setNombre(String.valueOf(request.getParameter("txtNombre")).toUpperCase());
                if (socio.getNombre().isEmpty()) {
                    errores += "Debes ingresar el nombre del socio. <br />";
                }

                socio.setRut_socio(String.valueOf(request.getParameter("txtRut")).toUpperCase());
                if (socio.getRut_socio().isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (socio.getRut_socio().length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (socio.getRut_socio().length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(socio.getRut_socio())) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                socio.setCodigo(String.valueOf(request.getParameter("cboSindicato")));
                if (socio.getCodigo().equals("null")) {
                    errores += "Debes seleccionar el sindicato al cual pertenece el socio. <br />";
                }

                socio.setDireccion(String.valueOf(request.getParameter("txtDireccion").toUpperCase()));
                if (socio.getDireccion().isEmpty()) {
                    errores += "Debes ingresar la dirección del socio. <br />";
                }

                socio.setSexo(String.valueOf(request.getParameter("optSexo")));
                if (socio.getSexo().equals("null")) {
                    errores += "Debes seleccionar el sexo del socio. <br />";
                }

                if (numeroEntero(String.valueOf(request.getParameter("txtEdad")))) {
                    socio.setEdad(Integer.parseInt(String.valueOf(request.getParameter("txtEdad"))));
                } else {
                    errores += "Debes ingresar solo números en el campo de edad. <br />";
                }
                if (socio.getEdad() < 10 || socio.getEdad() > 120) {
                    errores += "Debes ingresar una edad válida. <br />";
                }

                socio.setFecha_nacimiento(String.valueOf(request.getParameter("txtFecha_Nacimiento")).toUpperCase());
                if (socio.getFecha_nacimiento().isEmpty()) {
                    errores += "Debes ingresar la fecha de nacimiento. <br />";
                }

                String comuna_residencia = "";
                String comprobacionCodigoComuna = String.valueOf(request.getParameter("cboComuna"));
                if (comprobacionCodigoComuna.equals("null")) {
                    errores += "Debes seleccionar una comuna de residencia. <br />";
                } else {
                    socio.setCodigo_comuna_residencia(Integer.parseInt(String.valueOf(request.getParameter("cboComuna"))));
                    //Consulta sobre el nombre de la comuna
                    comuna_residencia = dao.obtenerNombreComuna(socio.getCodigo_comuna_residencia());
                    socio.setComuna_residencia(comuna_residencia);
                }

                String condicion_comuna_antigua = String.valueOf(request.getParameter("cboComunaAntigua"));
                if (condicion_comuna_antigua.equals("null")) {
                    errores += "Debes seleccionar una comuna de residencia antigua o indicar si es la misma que la comuna actual. <br />";
                } else {
                    //Revisión de condición sobre la comuna antigua
                    if (condicion_comuna_antigua.equals("LA_MISMA")) {
                        socio.setComuna_antigua(comuna_residencia);
                        socio.setCodigo_comuna_antigua(Integer.parseInt(String.valueOf(request.getParameter("cboComuna"))));
                    } else {
                        socio.setCodigo_comuna_antigua(Integer.parseInt(String.valueOf(request.getParameter("cboComunaAntigua"))));
                        socio.setComuna_antigua(dao.obtenerNombreComuna(socio.getCodigo_comuna_antigua()));
                    }
                }

                socio.setNacionalidad(String.valueOf(request.getParameter("cboNacionalidad")));
                if (socio.getNacionalidad().equals("null")) {
                    errores += "Debes seleccionar una nacionalidad. <br />";
                }

                socio.setEducacion(String.valueOf(request.getParameter("cboEducacion")));
                if (socio.getEducacion().equals("null")) {
                    errores += "Debes indicar el nivel educativo. <br />";
                }

                String comprobacionMiembrosHogar = String.valueOf(request.getParameter("cboMiembrosHogar"));
                if (comprobacionMiembrosHogar.equals("null")) {
                    errores += "Debes seleccionar cuantos miembros constituyen el hogar. <br />";
                } else {
                    socio.setMiembros_hogar(Integer.parseInt(String.valueOf(request.getParameter("cboMiembrosHogar"))));
                }

                String comprobadorProveedor = String.valueOf(request.getParameter("optProveedor"));
                if (comprobadorProveedor.equals("null")) {
                    errores += "Debes seleccionar si se trata del principal proveedor. <br />";
                } else {
                    socio.setProveedor_principal(Integer.parseInt(String.valueOf(request.getParameter("optProveedor"))));
                }

                String comprobadorPersonasTrabajando = String.valueOf(request.getParameter("cboTrabajandoRemuneradamente"));
                if (comprobadorPersonasTrabajando.equals("null")) {
                    errores += "Debes indicar cuantas personas del hogar se encuentran trabajando remuneradamente. <br />";
                } else {
                    if (socio.getMiembros_hogar() < Integer.parseInt(comprobadorPersonasTrabajando)) {
                        errores += "El número de familiares trabajando no puede ser superior al número de integrantes del hogar. <br />";
                    } else {
                        socio.setPersonas_trabajando(Integer.parseInt(String.valueOf(request.getParameter("cboTrabajandoRemuneradamente"))));
                    }
                }

                String comprobadorPersonasBuscando = String.valueOf(request.getParameter("cboBuscandoTrabajo"));
                if (comprobadorPersonasBuscando.equals("null")) {
                    errores += "Debes indicar cuantas personas del hogar se encuentran buscando trabajo. <br />";
                } else {
                    if (socio.getMiembros_hogar() < Integer.parseInt(comprobadorPersonasBuscando)) {
                        errores += "El número de familiares buscando trabajo no puede ser superior al número de integrantes del hogar. <br />";
                    } else {
                        socio.setPersonas_buscando(Integer.parseInt(String.valueOf(request.getParameter("cboBuscandoTrabajo"))));
                    }
                }

                String comprobadorCasaPropia = String.valueOf(request.getParameter("cboVivienda"));
                if (comprobadorCasaPropia.equals("null")) {
                    errores += "Debes seleccionar un tipo de relación con la vivienda que habitas. <br />";
                } else {
                    socio.setCasa_propia(String.valueOf(request.getParameter("cboVivienda")));
                }

                String comprobadorOtroHogar = String.valueOf(request.getParameter("optOtroHogar"));
                if (comprobadorOtroHogar.equals("null")) {
                    errores += "Debes indicar si el trabajador aporta a otro hogar. <br />";
                } else {
                    socio.setOtro_hogar(Integer.parseInt(String.valueOf(request.getParameter("optOtroHogar"))));
                }

                String comprobadorExperiencia = String.valueOf(request.getParameter("cboAniosExperiencia"));
                if (comprobadorExperiencia.equals("null")) {
                    errores += "Debes indicar tus años de experiencia laboral. <br />";
                } else {
                    socio.setExperiencia_años(Integer.parseInt(String.valueOf(request.getParameter("cboAniosExperiencia"))));
                }

                String comprobadorOcupacionAparte = String.valueOf(request.getParameter("optOtraOcupacion"));
                if (comprobadorOcupacionAparte.equals("null")) {
                    errores += "Debes indicar si el trabajador posee otra ocupación. <br />";
                } else {
                    socio.setOcupacion_aparte(Integer.parseInt(String.valueOf(request.getParameter("optOtraOcupacion"))));
                }

                socio.setActividad_principal(String.valueOf(request.getParameter("optMayoresIngresos")));
                if (socio.getActividad_principal().equals("null")) {
                    errores += "Debes indicar si el trabajo portuario es o no la ocupación principal. <br />";
                }

                String comprobadorMasPuerto = String.valueOf(request.getParameter("optMasPuerto"));
                if (comprobadorMasPuerto.equals("null")) {
                    errores += "Debes indicar si el trabajador se desempeña en más de un puerto o no. <br />";
                } else {
                    socio.setMas_puerto(Integer.parseInt(String.valueOf(request.getParameter("optMasPuerto"))));
                }

                String comprobadorNumeroPuertos = String.valueOf(request.getParameter("cboNumeroPuertos"));
                if (comprobadorNumeroPuertos.equals("null")) {
                    errores += "Debes indicar en cuantos puertos trabajaste en el último año. <br />";
                } else {
                    if (socio.getMas_puerto() == 0 && Integer.parseInt(comprobadorNumeroPuertos) > 1) {
                        errores += "El número de puertos en los que trabajaste no puede ser superior a uno. <br />";
                    } else if (socio.getMas_puerto() == 1 && Integer.parseInt(comprobadorNumeroPuertos) < 2) {
                        errores += "El número de puertos en los que trabajaste no puede ser inferior a dos. <br />";
                    } else {
                        socio.setNumero_puertos(Integer.parseInt(String.valueOf(request.getParameter("cboNumeroPuertos"))));
                    }
                }

                String comprobadorFamilia_Puertos = String.valueOf(request.getParameter("familiarPuerto"));
                if (comprobadorFamilia_Puertos.equals("null")) {
                    errores += "Debes indicar si posees o no familiares trabajando en los puertos. <br />";
                } else {
                    socio.setFamilia_puertos(Integer.parseInt(String.valueOf(request.getParameter("familiarPuerto"))));
                }

                if (socio.getFamilia_puertos() == 1) {
                    socio.setRut_familiar_uno(String.valueOf(request.getParameter("txtRutFamiliarUno")).toUpperCase());
                    if (socio.getRut_familiar_uno().isEmpty()) {
                        errores += "Debes ingresar el rut del primer familiar que trabaja en puertos. <br />";
                    } else if (!socio.getRut_familiar_uno().isEmpty() && socio.getRut_familiar_uno().length() < 11) {
                        errores += "El número de caracteres ingresados en el rut del primer familiar es menor al estándar. <br />";
                    } else if (!socio.getRut_familiar_uno().isEmpty() && socio.getRut_familiar_uno().length() > 12) {
                        errores += "El número de caracteres ingresados en el rut del primer familiar es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                    } else if (!socio.getRut_familiar_uno().isEmpty() && !validarRut(socio.getRut_familiar_uno())) {
                        errores += "El rut ingresado del primer familiar no es válido. <br />";
                    }
                    
                    socio.setRut_familiar_dos(String.valueOf(request.getParameter("txtRutFamiliarDos")).toUpperCase());
                    if (!socio.getRut_familiar_dos().isEmpty() && socio.getRut_socio().length() < 11) {
                        errores += "El número de caracteres ingresados en el rut del segundo familiar es menor al estándar. <br />";
                    } else if (!socio.getRut_familiar_dos().isEmpty() && socio.getRut_familiar_dos().length() > 12) {
                        errores += "El número de caracteres ingresados en el rut del segundo familiar es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                    } else if (!socio.getRut_familiar_dos().isEmpty() && !validarRut(socio.getRut_familiar_dos())) {
                        errores += "El rut ingresado del segundo familiar no es válido. <br />";
                    } else if (socio.getRut_familiar_dos().isEmpty()) {
                        socio.setRut_familiar_dos("VACÍO");
                    }
                } else {
                    socio.setRut_familiar_uno("VACÍO");
                    socio.setRut_familiar_dos("VACÍO");
                }

                socio.setAfiliado(String.valueOf(request.getParameter("optAfiliado")));
                if (socio.getAfiliado().equals("null")) {
                    errores += "Debes indicar si el trabajador está o no afiliado. <br />";
                }

                socio.setAfp(String.valueOf(request.getParameter("optAfp")));
                if (socio.getAfp().equals("null")) {
                    errores += "Debes seleccionar la AFP del trabajador, o indicar que no cotiza en AFP. <br />";
                }

                String comprobadorCotizacion = String.valueOf(request.getParameter("optCotiza"));
                if (comprobadorCotizacion.equals("null")) {
                    errores += "Debes indicar si el trabajador cotiza o no. <br />";
                } else {
                    socio.setCotizacion(Integer.parseInt(String.valueOf(request.getParameter("optCotiza"))));
                }

                String comprobadorIsapre = String.valueOf(request.getParameter("optIsapre"));
                if (comprobadorIsapre.equals("null")) {
                    errores += "Debes indicar si el trabajador está o no afiliado a alguna ISAPRE. <br />";
                } else {
                    socio.setIsapre(Integer.parseInt(String.valueOf(request.getParameter("optIsapre"))));
                }

                socio.setCanal_informacion(String.valueOf(request.getParameter("cboCanal")));
                if (socio.getCanal_informacion().equals("null")) {
                    errores += "Debes indicar el canal de información del socio. <br />";
                }

                //Consultar existencia previa del rut
                boolean existencia = false;
                existencia = dao.existenciaPrevia(socio.getRut_socio());
                if (existencia) {
                    errores += "El rut que ha tratado de ingresar ya existe en el sistema. <br />";
                }

                if (errores.isEmpty()) {

                    int cantFilas = dao.registrarSocio(socio);

                    //Verificar la inserción y enviar mensajes.
                    if (cantFilas > 0) {
                        dao.ingresarAuditoria(id_ingreso, "INGRESAR", "SOCIO", socio.getRut_socio());
                        msg = "Trabajador ingresado exitosamente";
                    } else {
                        msg = "Error en el ingreso";
                    }

                    request.setAttribute("msg", msg);
                } else {
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosSocio.do").forward(request, response);

            } else if (operacion.equals("modificar")) {
                int id_ingreso;
                socio = new Socio();

                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Llenado de variables y validación de datos para el objeto socio
                //Llenado de variables y validación de datos para el objeto socio
                socio.setNombre(String.valueOf(request.getParameter("txtNombre")).toUpperCase());
                if (socio.getNombre().isEmpty()) {
                    errores += "Debes ingresar el nombre del socio. <br />";
                }

                socio.setRut_socio(String.valueOf(request.getParameter("txtRut")).toUpperCase());
                if (socio.getRut_socio().isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (socio.getRut_socio().length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (socio.getRut_socio().length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(socio.getRut_socio())) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                socio.setCodigo(String.valueOf(request.getParameter("cboSindicato")));
                if (socio.getCodigo().equals("null")) {
                    errores += "Debes seleccionar el sindicato al cual pertenece el socio. <br />";
                }

                socio.setDireccion(String.valueOf(request.getParameter("txtDireccion")).toUpperCase());
                if (socio.getDireccion().isEmpty()) {
                    errores += "Debes ingresar la dirección del socio. <br />";
                }

                socio.setSexo(String.valueOf(request.getParameter("optSexo")));
                if (socio.getSexo().equals("null")) {
                    errores += "Debes seleccionar el sexo del socio. <br />";
                }

                if (numeroEntero(String.valueOf(request.getParameter("txtEdad")))) {
                    socio.setEdad(Integer.parseInt(String.valueOf(request.getParameter("txtEdad"))));
                } else {
                    errores += "Debes ingresar solo números en el campo de edad. <br />";
                }
                if (socio.getEdad() < 10 || socio.getEdad() > 120) {
                    errores += "Debes ingresar una edad válida. <br />";
                }

                socio.setFecha_nacimiento(String.valueOf(request.getParameter("txtFecha_Nacimiento")).toUpperCase());
                if (socio.getFecha_nacimiento().isEmpty()) {
                    errores += "Debes ingresar la fecha de nacimiento. <br />";
                }

                String comuna_residencia = "";
                String comprobacionCodigoComuna = String.valueOf(request.getParameter("cboComuna"));
                if (comprobacionCodigoComuna.equals("null")) {
                    errores += "Debes seleccionar una comuna de residencia. <br />";
                } else {
                    socio.setCodigo_comuna_residencia(Integer.parseInt(String.valueOf(request.getParameter("cboComuna"))));
                    //Consulta sobre el nombre de la comuna
                    comuna_residencia = dao.obtenerNombreComuna(socio.getCodigo_comuna_residencia());
                    socio.setComuna_residencia(comuna_residencia);
                }

                String condicion_comuna_antigua = String.valueOf(request.getParameter("cboComunaAntigua"));
                if (condicion_comuna_antigua.equals("null")) {
                    errores += "Debes seleccionar una comuna de residencia antigua o indicar si es la misma que la comuna actual. <br />";
                } else {
                    //Revisión de condición sobre la comuna antigua
                    if (condicion_comuna_antigua.equals("LA_MISMA")) {
                        socio.setComuna_antigua(comuna_residencia);
                        socio.setCodigo_comuna_antigua(Integer.parseInt(String.valueOf(request.getParameter("cboComuna"))));
                    } else {
                        socio.setCodigo_comuna_antigua(Integer.parseInt(String.valueOf(request.getParameter("cboComunaAntigua"))));
                        socio.setComuna_antigua(dao.obtenerNombreComuna(socio.getCodigo_comuna_antigua()));
                    }
                }

                socio.setNacionalidad(String.valueOf(request.getParameter("cboNacionalidad")));
                if (socio.getNacionalidad().equals("null")) {
                    errores += "Debes seleccionar una nacionalidad. <br />";
                }

                socio.setEducacion(String.valueOf(request.getParameter("cboEducacion")));
                if (socio.getEducacion().equals("null")) {
                    errores += "Debes indicar el nivel educativo. <br />";
                }

                String comprobacionMiembrosHogar = String.valueOf(request.getParameter("cboMiembrosHogar"));
                if (comprobacionMiembrosHogar.equals("null")) {
                    errores += "Debes seleccionar cuantos miembros constituyen el hogar. <br />";
                } else {
                    socio.setMiembros_hogar(Integer.parseInt(String.valueOf(request.getParameter("cboMiembrosHogar"))));
                }

                String comprobadorProveedor = String.valueOf(request.getParameter("optProveedor"));
                if (comprobadorProveedor.equals("null")) {
                    errores += "Debes seleccionar si se trata del principal proveedor. <br />";
                } else {
                    socio.setProveedor_principal(Integer.parseInt(String.valueOf(request.getParameter("optProveedor"))));
                }

                String comprobadorPersonasTrabajando = String.valueOf(request.getParameter("cboTrabajandoRemuneradamente"));
                if (comprobadorPersonasTrabajando.equals("null")) {
                    errores += "Debes indicar cuantas personas del hogar se encuentran trabajando remuneradamente. <br />";
                } else {
                    if (socio.getMiembros_hogar() < Integer.parseInt(comprobadorPersonasTrabajando)) {
                        errores += "El número de familiares trabajando no puede ser superior al número de integrantes del hogar. <br />";
                    } else {
                        socio.setPersonas_trabajando(Integer.parseInt(String.valueOf(request.getParameter("cboTrabajandoRemuneradamente"))));
                    }
                }

                String comprobadorPersonasBuscando = String.valueOf(request.getParameter("cboBuscandoTrabajo"));
                if (comprobadorPersonasBuscando.equals("null")) {
                    errores += "Debes indicar cuantas personas del hogar se encuentran buscando trabajo. <br />";
                } else {
                    if (socio.getMiembros_hogar() < Integer.parseInt(comprobadorPersonasBuscando)) {
                        errores += "El número de familiares buscando trabajo no puede ser superior al número de integrantes del hogar. <br />";
                    } else {
                        socio.setPersonas_buscando(Integer.parseInt(String.valueOf(request.getParameter("cboBuscandoTrabajo"))));
                    }
                }

                String comprobadorCasaPropia = String.valueOf(request.getParameter("cboVivienda"));
                if (comprobadorCasaPropia.equals("null")) {
                    errores += "Debes seleccionar un tipo de relación con la vivienda que habitas. <br />";
                } else {
                    socio.setCasa_propia(String.valueOf(request.getParameter("cboVivienda")));
                }

                String comprobadorOtroHogar = String.valueOf(request.getParameter("optOtroHogar"));
                if (comprobadorOtroHogar.equals("null")) {
                    errores += "Debes indicar si el trabajador aporta a otro hogar. <br />";
                } else {
                    socio.setOtro_hogar(Integer.parseInt(String.valueOf(request.getParameter("optOtroHogar"))));
                }

                String comprobadorExperiencia = String.valueOf(request.getParameter("cboAniosExperiencia"));
                if (comprobadorExperiencia.equals("null")) {
                    errores += "Debes indicar tus años de experiencia laboral. <br />";
                } else {
                    socio.setExperiencia_años(Integer.parseInt(String.valueOf(request.getParameter("cboAniosExperiencia"))));
                }

                String comprobadorOcupacionAparte = String.valueOf(request.getParameter("optOtraOcupacion"));
                if (comprobadorOcupacionAparte.equals("null")) {
                    errores += "Debes indicar si el trabajador posee otra ocupación. <br />";
                } else {
                    socio.setOcupacion_aparte(Integer.parseInt(String.valueOf(request.getParameter("optOtraOcupacion"))));
                }

                socio.setActividad_principal(String.valueOf(request.getParameter("optMayoresIngresos")));
                if (socio.getActividad_principal().equals("null")) {
                    errores += "Debes indicar si el trabajo portuario es o no la ocupación principal. <br />";
                }

                String comprobadorMasPuerto = String.valueOf(request.getParameter("optMasPuerto"));
                if (comprobadorMasPuerto.equals("null")) {
                    errores += "Debes indicar si el trabajador se desempeña en más de un puerto o no. <br />";
                } else {
                    socio.setMas_puerto(Integer.parseInt(String.valueOf(request.getParameter("optMasPuerto"))));
                }

                String comprobadorNumeroPuertos = String.valueOf(request.getParameter("cboNumeroPuertos"));
                if (comprobadorNumeroPuertos.equals("null")) {
                    errores += "Debes indicar en cuantos puertos trabajaste en el último año. <br />";
                } else {
                    if (socio.getMas_puerto() == 0 && Integer.parseInt(comprobadorNumeroPuertos) > 1) {
                        errores += "El número de puertos en los que trabajaste no puede ser superior a uno. <br />";
                    } else if (socio.getMas_puerto() == 1 && Integer.parseInt(comprobadorNumeroPuertos) < 2) {
                        errores += "El número de puertos en los que trabajaste no puede ser inferior a dos. <br />";
                    } else {
                        socio.setNumero_puertos(Integer.parseInt(String.valueOf(request.getParameter("cboNumeroPuertos"))));
                    }
                }

                String comprobadorFamilia_Puertos = String.valueOf(request.getParameter("familiarPuerto"));
                if (comprobadorFamilia_Puertos.equals("null")) {
                    errores += "Debes indicar si posees o no familiares trabajando en los puertos. <br />";
                } else {
                    socio.setFamilia_puertos(Integer.parseInt(String.valueOf(request.getParameter("familiarPuerto"))));
                }

                if (socio.getFamilia_puertos() == 1) {
                    socio.setRut_familiar_uno(String.valueOf(request.getParameter("txtRutFamiliarUno")).toUpperCase());
                    if (socio.getRut_familiar_uno().isEmpty()) {
                        errores += "Debes ingresar el rut del primer familiar que trabaja en puertos. <br />";
                    } else if (!socio.getRut_familiar_uno().isEmpty() && socio.getRut_familiar_uno().length() < 11) {
                        errores += "El número de caracteres ingresados en el rut del primer familiar es menor al estándar. <br />";
                    } else if (!socio.getRut_familiar_uno().isEmpty() && socio.getRut_familiar_uno().length() > 12) {
                        errores += "El número de caracteres ingresados en el rut del primer familiar es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                    } else if (!socio.getRut_familiar_uno().isEmpty() && !validarRut(socio.getRut_familiar_uno())) {
                        errores += "El rut ingresado del primer familiar no es válido. <br />";
                    }
                    
                    socio.setRut_familiar_dos(String.valueOf(request.getParameter("txtRutFamiliarDos")).toUpperCase());
                    if (!socio.getRut_familiar_dos().isEmpty() && socio.getRut_socio().length() < 11) {
                        errores += "El número de caracteres ingresados en el rut del segundo familiar es menor al estándar. <br />";
                    } else if (!socio.getRut_familiar_dos().isEmpty() && socio.getRut_familiar_dos().length() > 12) {
                        errores += "El número de caracteres ingresados en el rut del segundo familiar es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                    } else if (!socio.getRut_familiar_dos().isEmpty() && !validarRut(socio.getRut_familiar_dos())) {
                        errores += "El rut ingresado del segundo familiar no es válido. <br />";
                    } else if (socio.getRut_familiar_dos().isEmpty()) {
                        socio.setRut_familiar_dos("VACÍO");
                    }
                } else {
                    socio.setRut_familiar_uno("VACÍO");
                    socio.setRut_familiar_dos("VACÍO");
                }

                socio.setAfiliado(String.valueOf(request.getParameter("optAfiliado")));
                if (socio.getAfiliado().equals("null")) {
                    errores += "Debes indicar si el trabajador está o no afiliado. <br />";
                }

                socio.setAfp(String.valueOf(request.getParameter("optAfp")));
                if (socio.getAfp().equals("null")) {
                    errores += "Debes seleccionar la AFP del trabajador, o indicar que no cotiza en AFP. <br />";
                }

                String comprobadorCotizacion = String.valueOf(request.getParameter("optCotiza"));
                if (comprobadorCotizacion.equals("null")) {
                    errores += "Debes indicar si el trabajador cotiza o no. <br />";
                } else {
                    socio.setCotizacion(Integer.parseInt(String.valueOf(request.getParameter("optCotiza"))));
                }

                String comprobadorIsapre = String.valueOf(request.getParameter("optIsapre"));
                if (comprobadorIsapre.equals("null")) {
                    errores += "Debes indicar si el trabajador está o no afiliado a alguna ISAPRE. <br />";
                } else {
                    socio.setIsapre(Integer.parseInt(String.valueOf(request.getParameter("optIsapre"))));
                }

                socio.setCanal_informacion(String.valueOf(request.getParameter("cboCanal")));
                if (socio.getCanal_informacion().equals("null")) {
                    errores += "Debes indicar el canal de información del socio. <br />";
                }
                
                //Consultar existencia previa del rut
                boolean existencia = false;
                existencia = dao.existenciaPrevia(socio.getRut_socio());
                if (!existencia) {
                    errores += "El rut que ha tratado de modificar no existe en el sistema. <br />";
                }

                if (errores.isEmpty()) {

                    int cantFilas =  cantFilas = dao.modificarSocio(socio);

                    //Verificar la inserción y enviar mensajes.
                    if (cantFilas > 0) {
                        dao.ingresarAuditoria(id_ingreso, "MODIFICAR", "SOCIO", socio.getRut_socio());
                        msg = "Trabajador modificado exitosamente";
                    } else {
                        msg = "Error en la modificación";
                    }
                    request.setAttribute("msg", msg);
                } else {
                    errores += "<br/><b>Deberá volver a buscar el trabajador, e introducir los valores de modificación correctamente.</b> <br />";
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosSocio.do").forward(request, response);

            } else if (operacion.equals("eliminar")) {
                int id_ingreso;

                //Llenado de id de ingreso
                id_ingreso = Integer.parseInt(String.valueOf(request.getParameter("id_ingreso")));

                //Validación y llenado de variable rut
                String rut = String.valueOf(request.getParameter("txtRut")).toUpperCase();

                if (rut.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut)) {
                    errores += "El rut ingresado no es válido. <br />";
                }
                
                //Consultar existencia previa del rut
                boolean existencia = false;
                existencia = dao.existenciaPrevia(rut);
                if (!existencia) {
                    errores += "El rut que ha tratado de eliminar no existe en el sistema. <br />";
                }

                if (errores.isEmpty()) {

                    boolean confirmacion = dao.eliminarSocio(rut);
                    
                    //Verificar la inserción y enviar mensajes.
                    if (confirmacion) {
                        dao.ingresarAuditoria(id_ingreso, "ELIMINAR", "SOCIO", rut);
                        msg = "Trabajador eliminado exitosamente";
                    } else {
                        msg = "Error en la eliminación";
                    }
                    request.setAttribute("msg", msg);
                } else {
                    errores += "<br/><b>Deberá volver a buscar el trabajador.</b> <br />";
                    request.setAttribute("msg", errores);
                }

                //Se retorna el mensaje de vuelta al jsp.
                request.getRequestDispatcher("datosSocio.do").forward(request, response);

            } else if (operacion.equals("buscar")) {
                //Objeto socioBuscado
                socioBuscado = new SocioBuscado();
                //Llenado de variable rut
                String rut = String.valueOf(request.getParameter("txtRut")).toUpperCase();
                //validación de rut ingresado
                if (rut.isEmpty()) {
                    errores += "Debes ingresar el rut del socio. <br />";
                } else if (rut.length() < 11) {
                    errores += "El número de caracteres ingresados en el rut es menor al estándar. <br />";
                } else if (rut.length() > 12) {
                    errores += "El número de caracteres ingresados en el rut es mayor al estándar; recuerde no ingresar puntos ni el guión. <br />";
                } else if (!validarRut(rut)) {
                    errores += "El rut ingresado no es válido. <br />";
                }

                //Consulta al dao
                socio = dao.buscarSocio(rut);
                if (socio.getNombre() == null) {
                    errores += "No existe un trabajador con ese rut en el sistema. <br />";
                } else {
                    //Traspaso de objeto socio a socioBuscado
                    socioBuscado.setNombre(socio.getNombre());
                    socioBuscado.setRut_socio(socio.getRut_socio());
                    socioBuscado.setSindicato(dao.obtenerNombreSindicato(socio.getCodigo()));
                    socioBuscado.setSexo(socio.getSexo());
                    socioBuscado.setEdad(socio.getEdad());
                    socioBuscado.setFecha_nacimiento(socio.getFecha_nacimiento());
                    socioBuscado.setComuna_residencia(socio.getComuna_residencia());
                    socioBuscado.setComuna_antigua(socio.getComuna_antigua());
                    socioBuscado.setNacionalidad(socio.getNacionalidad());
                    socioBuscado.setEducacion(socio.getEducacion());
                    socioBuscado.setMiembros_hogar(socio.getMiembros_hogar());
                    if (socio.getProveedor_principal() == 1) {
                        socioBuscado.setProveedor_principal("SÍ");
                    } else {
                        socioBuscado.setProveedor_principal("NO");
                    }
                    socioBuscado.setPersonas_trabajando(socio.getPersonas_trabajando());
                    socioBuscado.setPersonas_buscando(socio.getPersonas_buscando());
                    socioBuscado.setVivienda(socio.getCasa_propia());
                    if (socio.getOtro_hogar() == 1) {
                        socioBuscado.setOtro_hogar("SÍ");
                    } else {
                        socioBuscado.setOtro_hogar("NO");
                    }
                    socioBuscado.setExperiencia_años(socio.getExperiencia_años());
                    if (socio.getOcupacion_aparte() == 1) {
                        socioBuscado.setOcupacion_aparte("SÍ");
                    } else {
                        socioBuscado.setOcupacion_aparte("NO");
                    }
                    socioBuscado.setActividad_principal(socio.getActividad_principal());
                    if (socio.getMas_puerto() == 1) {
                        socioBuscado.setMas_puerto("SÍ");
                    } else {
                        socioBuscado.setMas_puerto("NO");
                    }
                    socioBuscado.setNumero_puertos(socio.getNumero_puertos());
                    if(socio.getFamilia_puertos() ==1){
                        socioBuscado.setFamilia_puertos("SÍ");
                    } else {
                        socioBuscado.setFamilia_puertos("NO");
                    }
                    socioBuscado.setDireccion(socio.getDireccion());
                    socioBuscado.setRut_familiar_uno(socio.getRut_familiar_uno());
                    socioBuscado.setRut_familiar_dos(socio.getRut_familiar_dos());
                    socioBuscado.setAfiliado(socio.getAfiliado());
                    socioBuscado.setAfp(socio.getAfp());
                    if (socio.getCotizacion() == 1) {
                        socioBuscado.setCotizacion("SÍ");
                    } else {
                        socioBuscado.setCotizacion("NO");
                    }
                    if (socio.getIsapre() == 1) {
                        socioBuscado.setIsapre("SÍ");
                    } else {
                        socioBuscado.setIsapre("NO");
                    }
                    socioBuscado.setCanal_informacion(socio.getCanal_informacion());
                }

                if (errores.isEmpty()) {
                    request.setAttribute("socioBuscado", socioBuscado);
                    request.getRequestDispatcher("datosSocioBuscado.do").forward(request, response);
                } else {
                    request.setAttribute("msg", errores);
                    request.getRequestDispatcher("datosSocio.do").forward(request, response);
                }

            }

        } else if (userPath.equals("/datosSocioBuscado.do")) {
            //Se recuperan los registros de los comboBox.
            listaSindicato = dao.listarSindicatos();
            listaComuna = dao.listarComunas();
            listaNacion = dao.listarNacion();
            socioBuscado = (SocioBuscado) request.getAttribute("socioBuscado");
            //Se envía información a jsp de salida.
            request.setAttribute("listaSindicato", listaSindicato);
            request.setAttribute("listaComuna", listaComuna);
            request.setAttribute("listaNacion", listaNacion);
            request.setAttribute("socioBuscado", socioBuscado);
            request.getRequestDispatcher("sociosBuscados.jsp").forward(request, response);

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
