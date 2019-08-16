<%-- 
    Document   : avance
    Created on : 04-04-2018, 11:26:57
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
                    <h2><b>Avance según Encuestador</b></h2>
                    <p class="flow-text" align="justify">Esta sección le permitirá corroborar la cantidad de ingresos de trabajadores que ya ha realizado.</p>
                    <p class="flow-text" align="justify">Le recordamos que el ingreso de un registro a la base de datos válido y correcto se compone de la información de un trabajador relativa a los tres módulos de este formulario, información del socio del módulo 'Ingreso de socios', la información del trabajo en puerto del módulo 'Ingreso de trabajo en puerto' y la información de la relación contractual con empresas del módulo 'Ingreso de empresas'.</p>
                    <p class="flow-text" align="justify">Según esto, en esta página le mostraremos la cantidad de ingresos válidos de socios trabajadores y la cantidad de ingresos parciales o inválidos.</p>
                    <p class="flow-text" align="justify">Las cantidades de ingresos válidos e inválidos serán acompañados por listados en donde aparecerán los ruts de los trabajadores que ha ingresado, en virtud de facilitar la corrección de los registros inválidos.</p>
                </div>
            </div>

            <div class="row">
                <div class="col s12 card-panel red lighten-4">
                    <h4><b>Información del Encuestador</b></h4>
                    <br/>
                    <p class="flow-text"><b>Nombre de Encuestador: </b><%=encuestador.getNombre()%></p>
                    <p class="flow-text"><b>Rut de Encuestador: </b><%=encuestador.getRut()%></p>
                    <p class="flow-text"><b>Nombre de Usuario del Encuestador: </b><%=encuestador.getUsuario()%></p>
                    <p class="flow-text"><b>Sindicato del Encuestador: </b><%=encuestador.getSindicato()%></p>
                    <br/>
                </div>
            </div>

            <div class="row">
                <div class="col s10 offset-s1">
                    <h4>Listado de Ingresos Válidos</h4>
                    <p class="flow-text"><b>Cantidad: </b>${requestScope.validos}</p>
                    <table>
                        <thead>
                            <tr>
                                <th>Rut del Trabajador</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.listaRutValidos}" var="v">
                            <tr>
                                <td>${v}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col s10 offset-s1">
                    <h4>Listado de Ingresos Inválidos</h4>
                    <p class="flow-text"><b>Cantidad: </b>${requestScope.invalidos}</p>
                    <table>
                        <thead>
                            <tr>
                                <th>Rut del Trabajador</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.listaRutInvalidos}" var="i">
                            <tr>
                                <td>${i}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
        </script>
    </body>
</html>
