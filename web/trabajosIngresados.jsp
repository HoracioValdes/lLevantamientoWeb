<%-- 
    Document   : trabajoPuerto
    Created on : 21-07-2017, 12:20:47
    Author     : Horacio
--%>

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
        <title>Formulario de Levantamiento</title>
        <script>

        </script>
    </head>
    <%
        Ingreso ingreso = (Ingreso) session.getAttribute("ingreso");
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
                    <h2><b>Consulta de Registros de Trabajo en Puerto</b></h2>
                    <p class="flow-text" align="justify">En esta ventana puede seleccionar un registro de trabajo en puerto para modificarlo o eliminarlo.</p>
                    <p class="flow-text" align="justify">Para seleccionar el registro haga click en el símbolo (+).</p>
                </div>
                
                <br/>
                <p class="flow-text">&nbsp;</p>
                <br/>
                <div class="col s10 offset-s1"> 
                    <table class="bordered">
                        <thead>
                            <tr>
                                <th>Sindicato</th>
                                <th>Puerto</th>
                                <th> </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listaTrabajo}" var="t">
                                <tr>
                                    <td>${t.sindicato}</td>
                                    <td>${t.puerto}</td>
                                    <td>
                                        <a class="btn-floating red" href="trabajoBuscado.do?id_puerto=${t.id_puerto}&rut_socio=${t.rut_socio}">
                                            <i class="material-icons left">add</i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <br/>
            <p class="flow-text">&nbsp;</p>



            <footer class="page-footer red darken-1">
                <div class="container">
                    <div class="row">
                        <div class="col l6 s12">
                            <h5 class="white-text">Contáctanos</h5>
                            <p class="grey-text text-lighten-4">Envíanos tus sugerencias o comentarios a <a href="mailto:horacio.valdes@fundacionsol.cl" style="color: #ffff00">horacio.valdes@fundacionsol.cl</a></p>
                        </div>
                    </div>
                </div>
                <div class="footer-copyright">
                    <div class="container">
                        © 2017 Fundación SOL
                        <a class="grey-text text-lighten-4 right" href="http://www.fundacionsol.cl">Página de Fundación SOL</a>
                    </div>
                </div>
            </footer>

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
