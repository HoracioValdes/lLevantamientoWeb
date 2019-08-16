<%-- 
    Document   : nav
    Created on : 21-07-2017, 11:56:08
    Author     : Horacio
--%>

<%@page import="cl.modelo.Ingreso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav>
    <div class="nav-wrapper red darken-1">
        <a href="#" class="brand-logo">&nbsp;<img class="responsive-img" src="img/LOGO BLANCO SOL Y PORTUARIOS.png" width="120px" style="margin-top: 15px">&nbsp;</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="datosSocio.do"><b>Ingreso de socios</b></a></li>
            <li><a href="datosPuerto.do"><b>Ingreso de trabajo en puerto</b></a></li>
            <li><a href="datosEmpresa.do"><b>Ingreso de empresas</b></a></li>
            <li><a href="avance.do?id_ingreso=${sessionScope.ingreso.id}"><b>Avance</b></a></li>
            <li><a href="registro.jsp"><b>Registro</b></a></li>
            <li><a href="cerrar.jsp"><b>Salir</b></a></li>
        </ul>
        <ul class="side-nav red darken-1" id="mobile-demo">
            <li><a href="datosSocio.do" style="color: white"><b>Ingreso de socios</b></a></li>
            <li><a href="datosPuerto.do" style="color: white"><b>Ingreso de trabajo en puerto</b></a></li>
            <li><a href="datosEmpresa.do" style="color: white"><b>Ingreso de empresas</b></a></li>
            <li><a href="avance.do?id_ingreso=${sessionScope.ingreso.id}" style="color: white"><b>Avance</b></a></li>
            <li><a href="registro.jsp" style="color: white"><b>Registro</b></a></li>
            <li><a href="cerrar.jsp" style="color: white"><b>Salir</b></a></li>
        </ul>
    </div>
</nav>
