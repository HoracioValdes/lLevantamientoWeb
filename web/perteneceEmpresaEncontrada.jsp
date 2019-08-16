<%-- 
    Document   : trabajoPuerto
    Created on : 21-07-2017, 12:20:47
    Author     : Horacio
--%>

<%@page import="cl.modelo.EmpresaBuscada"%>
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

            function mostrar(cboMesesTrabajados) {
                alert(cboMesesTrabajados);
            }
        </script>
    </head>
    <%
        Ingreso ingreso = (Ingreso) session.getAttribute("ingreso");
        EmpresaBuscada empresaBuscada = (EmpresaBuscada) request.getAttribute("empresaBuscada");
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
                    <h2><b>Ingreso de Pertenencia a Empresa</b></h2>
                    <p class="flow-text" align="justify">En este formulario puede eliminar los datos de empresas asociadas a algún trabajador, o modificar el tipo de contrato que el trabajador sostiene con dicha empresa.</p>
                    <p class="flow-text" align="justify">Para modificar el tipo de contrato deberá seleccionarlo de la lista.</p>
                    <p class="flow-text" align="justify">Para eliminar los datos no es necesario rellenar nuevamente este formulario.</p>
                </div>
            </div>

            <div class="row"> 
                <form class="col s10 offset-s1" name="form" action="empresa.do?id_ingreso=${sessionScope.ingreso.id}" method="post">
                    <div class="col s12 card-panel red lighten-4">
                        <h4><b>Resultado de búsqueda</b></h4>
                        <br/>
                        <p class="flow-text"><b>Rut del trabajador: </b><%=empresaBuscada.getRut_socio()%></p>
                        <p class="flow-text"><b>Rut de la empresa: </b><%=empresaBuscada.getRut_empresa()%></p>
                        <p class="flow-text"><b>Nombre de la agencia: </b><%=empresaBuscada.getNombre_agencia()%></p>
                        <p class="flow-text"><b>Nombre del puerto: </b><%=empresaBuscada.getNombre_puerto()%></p>
                        <p class="flow-text"><b>Tipo de contrato: </b><%=empresaBuscada.getTipo_contrato()%></p>
                        <br/>
                    </div>
                    <br/>
                    <p class="flow-text">&nbsp;</p>
                    <br/>

                    <h4>IDENTIFICACIÓN DE TRABAJO EN PUERTO</h4>
                    <p class="flow-text">2 Y 21. Indique nuevamente el rut y el puerto del registro de trabajo (no editable)</p>
                    <label>Rut</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtRut" id="rut" type="text" class="validate" value="<%=empresaBuscada.getRut_socio()%>" maxlength="12" readonly="true">
                        </div>
                        <div class="input-field col s12">
                            <select name="cboPuerto" id="cboPuerto">
                                <option value="<%=empresaBuscada.getId_puerto()%>" selected="true"><%=empresaBuscada.getNombre_puerto()%></option>
                            </select>
                            <label for="cboPuerto">Puerto</label>
                        </div>
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="buscar" disabled="true">CARGAR DATOS INGRESADOS<i class="material-icons right">find_in_page</i></button>
                    </div>

                    <br/>
                    <h4>MÓDULO IV: TRABAJO EN PUERTO (Complementario a página de 'Ingreso de trabajo en puerto' (Menú de navegación)</h4>
                    <p class="flow-text">27. Señale la o las empresas en que ha trabajado el último año en cada puerto (no editable)</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboEmpresa" id="cboEmpresa">
                                <option value="<%=empresaBuscada.getRut_empresa()%>" selected="true"><%=empresaBuscada.getNombre_agencia()%></option>
                            </select>
                            <label for="cboEmpresa">Empresa</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">28. Señale su tipo de contrato con la o las empresas</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboTipoContrato" id="cboTipoContrato">
                                <option disabled selected>SELECCIONE TIPO DE CONTRATO</option>
                                <option value="INDEFINIDO">INDEFINIDO</option>
                                <option value="A PLAZO">A PLAZO</option>
                                <option value="EVENTUAL">EVENTUAL</option>
                            </select>
                            <label for="cboTipoContrato">Tipo de Contrato</label>
                        </div>
                    </div>

                    <p class="flow-text">&nbsp;</p>
                    <br/>
                    <div class="row">
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="ingresar" disabled="true">Ingresar<i class="material-icons right">send</i></button>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoOnce();" value="modificar">Modificar<i class="material-icons right">clear</i></button>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoDoce();" value="eliminar">Eliminar<i class="material-icons right">clear</i></button>
                    </div>

                    <br/>
                    <div class="row">
                        <a class="waves-effect waves-light btn" href="datosEmpresa.do"><i class="material-icons left">backspace</i>Limpiar formulario</a>
                    </div>
                </form>
            </div>

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

                            function probandoOnce() {

                                var campoRut = document.getElementById("rut").value;
                                var indiceTipoContrato = document.getElementById("cboTipoContrato").selectedIndex;

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
                                } else if (indiceTipoContrato == null || indiceTipoContrato == 0) {
                                    alert('[ERROR] Debe seleccionar un tipo de contrato en el campo 28');
                                    return false;
                                }
                                return true;
                            }

                            function probandoDoce() {

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
