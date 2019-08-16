<%-- 
    Document   : registro
    Created on : 27-04-2018, 15:45:59
    Author     : Horacio
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="cl.modelo.Encuestador"%>
<%@page import="cl.modelo.Ingreso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

        <!--favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="img/phpThumb_generated_thumbnailico" />
        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Avance según Encuestador</title>
        <script>
            function limpiar_rut(rut) {
                var rutLimpio = rut;
                //Definimos los caracteres a eliminar
                var eliminar = ".-";
                //Los eliminamos
                for (var i = 0; i < eliminar.length; i++) {
                    rutLimpio = rutLimpio.replace(new RegExp("\\" + eliminar[i], 'gi'), '');
                }
                //Pasamos al campo el valor limpio
                document.getElementById("rut").value = rutLimpio.toUpperCase();
            }

            function formato_rut(rut) {
                var sRut1 = rut; //Contador de posición
                var nPos = 0; //Guarda el rut invertido con los puntos y el guión agregado
                var sInvertido = ""; //Guarda el resultado final del rut como debe ser
                var sRut = "";
                for (var i = sRut1.length - 1; i >= 0; i--) {
                    sInvertido += sRut1.charAt(i);
                    if (i == sRut1.length - 1) {
                        sInvertido += "-";
                    } else if (nPos == 3) {
                        sInvertido += ".";
                        nPos = 0;
                    }
                    nPos++;
                }
                for (var j = sInvertido.length - 1; j >= 0; j--) {
                    if (sInvertido.charAt(sInvertido.length - 1) != ".") {
                        sRut += sInvertido.charAt(j);
                    } else if (j != sInvertido.length - 1) {
                        sRut += sInvertido.charAt(j);
                    }
                }
                //Pasamos al campo el valor formateado
                document.getElementById("rut").value = sRut.toUpperCase();
            }
        </script>
    </head>
    <%
        Ingreso ingreso = (Ingreso) session.getAttribute("ingreso");
        Encuestador encuestador = (Encuestador) request.getAttribute("encuestador");
    %>
    <body>
        <div class="container">
            <% if (ingreso != null) {%>

            <div class="row">
                <div class="col s12">
                    <jsp:include page="nav.jsp" />
                </div>
            </div>

            <div class="row">
                <div class="col s10 offset-s1">
                    <h2><b>Consulta de Registro</b></h2>
                    <p class="flow-text" align="justify">Esta sección le permitirá consultar la validez de un registro de un trabajador en particular.</p>
                    <p class="flow-text" align="justify">Le recordamos que el ingreso de un registro a la base de datos válido y correcto se compone de la información de un trabajador relativa a los tres módulos de este formulario, información del socio del módulo 'Ingreso de socios', la información del trabajo en puerto del módulo 'Ingreso de trabajo en puerto' y la información de la relación contractual con empresas del módulo 'Ingreso de empresas'.</p>
                    <p class="flow-text" align="justify">Según esto, en esta página le mostraremos si el registro que consulta por el rut es válido y que información le falta, en caso de que sea inválido.</p>
                </div>
            </div>

            <div class="row">
                <form class="col s10 offset-s1" action="registro.do" method="post">

                    <br/>
                    <p class="flow-text">Indique el rut del trabajador</p>
                    <label>Rut</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtRut" id="rut" type="text" class="validate" maxlength="12" placeholder="Ingrese el rut sin puntos (.) ni guión (-)" onblur="limpiar_rut(this.value);
                                    formato_rut(this.value);">
                        </div>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoDos();" value="buscar">Buscar<i class="material-icons right">find_in_page</i></button>
                    </div>

                </form>
            </div>

            <div class="row">
                <div class="col s12 card-panel red lighten-4">
                    <h4><b>Información del Trabajador</b></h4>
                    <br/>
                    <p class="flow-text"><b>¿Es válido el registro?: </b></p>
                    <p class="flow-text"><b>Nombre del Trabajador: </b></p>
                    <p class="flow-text"><b>Rut del Trabajador: </b></p>
                    <p class="flow-text"><b>Sindicato del Trabajador: </b></p>
                    <p class="flow-text"><b>¿Posee información de 'Trabajo en Puerto'?: </b></p>
                    <p class="flow-text"><b>¿Posee información de 'Pertenencia a Empresa'?: </b></p>
                    <br/>
                </div>
            </div>

            <% } else { %>
            <div class="row">
                <div class="col s12">
                    <h3>Debe iniciar sesión para acceder a los contenidos</h3>
                    <a href="index.jsp">Ir al acceso</a>
                </div>
            </div>
            <% }%>
        </div>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
                            $(function () {
                                $('select').material_select();
                                $(".button-collapse").sideNav();
                            });

                            function probandoDos() {

                                var campoRut = document.getElementById("rut").value;
                                if (campoRut == null || campoRut.length == 0 || /^\s+$/.test(campoRut)) {
                                    alert('[ERROR] El campo 2, que indica el rut, no puede estar vacío');
                                    return false;
                                } else if (campoRut.length < 11) {
                                    alert('[ERROR] El campo 2, que indica el rut, no puede tener menos de 11 caracteres');
                                    return false;
                                } else if (campoRut.length > 12) {
                                    alert('[ERROR] El campo 2, que indica el rut, no puede tener más de 12 caracteres');
                                    return false;
                                } else if (valrut(campoRut) == false) {
                                    alert('[ERROR] El rut ingresado en el campo 2 no es válido');
                                    return false;
                                }
                                return true;
                            }


                            function valrut(campoRut) {

                                var rut = campoRut;
                                var crut;
                                //limpieza
                                var eliminar = ".-";
                                //Los eliminamos
                                for (var i = 0; i < eliminar.length; i++) {
                                    rut = rut.replace(new RegExp("\\" + eliminar[i], 'gi'), '');
                                }

                                var tmpstr = "";
                                var intlargo = rut;
                                if (intlargo.length > 0)
                                {
                                    crut = rut;
                                    var largo = crut.length;
                                    if (largo < 2)
                                    {
                                        return false;
                                    }
                                    for (i = 0; i < crut.length; i++)
                                        if (crut.charAt(i) != ' ' && crut.charAt(i) != '.' && crut.charAt(i) != '-')
                                        {
                                            tmpstr = tmpstr + crut.charAt(i);
                                        }
                                    var rutSuma = tmpstr;
                                    crut = tmpstr;
                                    largo = crut.length;
                                    if (largo > 2)
                                        rutSuma = crut.substring(0, largo - 1);
                                    else
                                        rutSuma = crut.charAt(0);
                                    var dv = crut.charAt(largo - 1);
                                    if (rutSuma == null || dv == null)
                                        return false;
                                    var dvr = '0';
                                    var suma = 0;
                                    var mul = 2;
                                    for (i = rutSuma.length - 1; i >= 0; i--)
                                    {
                                        suma = suma + rutSuma.charAt(i) * mul;
                                        if (mul == 7)
                                            mul = 2;
                                        else
                                            mul++;
                                    }

                                    var res = suma % 11;
                                    if (res == 1)
                                        dvr = 'k';
                                    else if (res == 0)
                                        dvr = '0';
                                    else
                                    {
                                        var dvi = 11 - res;
                                        dvr = dvi + "";
                                    }

                                    if (dvr != dv.toLowerCase())
                                    {
                                        return false;
                                    }
                                    return true;
                                }
                            }
        </script>
    </body>
</html>

