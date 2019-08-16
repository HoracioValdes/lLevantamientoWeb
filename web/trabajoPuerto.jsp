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

            function mostrar(cboMesesTrabajados) {
                alert(cboMesesTrabajados);
            }
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
                    <h2><b>Ingreso de Trabajo en Puerto</b></h2>
                    <p class="flow-text" align="justify">En este formulario puede ingresar los datos del trabajo portuario de algún trabajador. Para ello, el trabajador debe haber sido previamente ingresado en la página 'Ingreso de Socios' (Menú de navegación).</p>
                    <p class="flow-text" align="justify">Para ingresar esta información debe ingresar nuevamente el rut del trabajador.</p>
                    <p class="flow-text" align="justify">También puede acceder al listado de trabajos portuarios previamente ingresados de algún trabajador. Para ello debe ingresar el rut y presionar el botón 'CARGAR DATOS INGRESADOS'.</p>
                </div>
            </div>

            <div class="row"> 
                <form class="col s10 offset-s1" name="form" action="puertos.do?id_ingreso=${sessionScope.ingreso.id}" method="post">
                    <div class="col s12 card-panel red lighten-4">
                        <h4><b>Observaciones de operación</b></h4>
                        <br/>
                        <p class="flow-text">${requestScope.msg}</p>
                        <br/>
                    </div>
                    <br/>
                    <p class="flow-text">&nbsp;</p>
                    <br/>

                    <h4>IDENTIFICACIÓN DE SOCIO</h4>
                    <p class="flow-text">2. Indique nuevamente el rut del trabajador</p>
                    <label>Rut</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtRut" id="rut" type="text" class="validate" placeholder="Ingrese el rut sin puntos (.) ni guión (-)" maxlength="12" onblur="limpiar_rut(this.value);formato_rut(this.value);">
                        </div>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoSeis();" value="buscar">CARGAR DATOS INGRESADOS<i class="material-icons right">find_in_page</i></button>
                    </div>

                    <br/>
                    <h4>MÓDULO IV: TRABAJO EN PUERTO</h4>
                    <p class="flow-text">21. ¿En qué puerto trabaja?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboPuerto" id="cboPuerto">
                                <option disabled selected>SELECCIONE PUERTO</option>
                                <c:forEach items="${requestScope.listaPuerto}" var="p">
                                    <option value="${p.id_puerto}">${p.nombre}</option>
                                </c:forEach>
                            </select>
                            <label for="cboPuerto">Puerto</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">22. ¿Qué meses trabajó en ese puerto durante el último año?</p>
                    <div class="input-field col s12">
                        <select name="cboMesesTrabajados" id="cboMesesTrabajados" multiple>
                            <option value="" disabled selected>SELECCIONE LOS MESES</option>
                            <option value="ENERO">ENERO</option>
                            <option value="FEBRERO">FEBRERO</option>
                            <option value="MARZO">MARZO</option>
                            <option value="ABRIL">ABRIL</option>
                            <option value="MAYO">MAYO</option>
                            <option value="JUNIO">JUNIO</option>
                            <option value="JULIO">JULIO</option>
                            <option value="AGOSTO">AGOSTO</option>
                            <option value="SEPTIEMBRE">SEPTIEMBRE</option>
                            <option value="OCTUBRE">OCTUBRE</option>
                            <option value="NOVIEMBRE">NOVIEMBRE</option>
                            <option value="DICIEMBRE">DICIEMBRE</option>
                        </select>
                        <label>Meses trabajados</label>
                    </div>

                    <p class="flow-text">&nbsp;</p>
                    <br/>
                    <p class="flow-text">23. ¿Cuántos turnos hizo el mes anterior?</p>
                    <label>Turnos</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtTurnos" id="turnos" type="text" maxlength="3" class="validate">
                        </div>
                    </div>
                    
                    <br/>
                    <p class="flow-text">24. Señale si ha sufrido algún accidente durante los últimos 3 años</p>
                    <label>Accidente</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optAccidente" type="radio" id="test9" value="1"/>
                            <label for="test9">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optAccidente" type="radio" id="test10" value="0"/>
                            <label for="test10">No</label>
                        </div>
                    </div>
                    
                    <br/>
                    <p class="flow-text">25. ¿En qué sección trabaja según puerto? (Considere la más frecuente)</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboSeccion" id="cboSeccion">
                                <option disabled selected>SELECCIONE LA SECCIÓN</option>
                                <option value="BUQUE-OPERACIONES MARÍTIMAS">BUQUE - OPERACIONES MARÍTIMAS</option>
                                <option value="PATIO-OPERACIONES DE TERMINAL">PATIO - OPERACIONES DE TERMINAL</option>
                                <option value="CORREA-CONECTIVIDAD DE PUERTOS">CORREA - CONECTIVIDAD DE PUERTOS</option>
                                <option value="ADMINISTRATIVO">ADMINISTRATIVO</option>
                            </select>
                            <label for="cboSeccion">Puerto</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">26. ¿Qué función realiza según puerto? (Considere la más frecuente)</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test1" value="OPERAR MAQUINARIA PESADA"/>
                            <label for="test1"><b>Operar maquinaria pesada:</b> Conductor, horquillero, winchero, operadores grúas, operadores de tracto camión, operador de winchas, etc.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test2" value="LABORES ADMINISTRATIVAS"/>
                            <label for="test2"><b>Supervisar y coordinar operaciones del puerto u otras labores administrativas:</b> Supervisor de almacén, supervisor terminal, supervisor de naves, coordinador depósito, etc.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test3" value="MANTENCIÓN Y MECÁNICA"/>
                            <label for="test3"><b>Realizar trabajos de mantención y mecánica:</b> Vulcanizador, auxiliar mecánico, técnico eléctrico, reparador embalador, soldador, jefe de mantención de equipos, etc.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test4" value="PROCESAR INFORMACIÓN"/>
                            <label for="test4"><b>Digitar, registrar y/o documentar información:</b> Digitador terminal, documental terminal, tarjador, etc.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test5" value="LABORES DE CARGA Y DESCARGA"/>
                            <label for="test5"><b>Desarrollar y/o apoyar labores de carga y descarga:</b> Portalonero o señalero, movilizador, estibador, trincador, auxiliar de embarque, auxiliar de nave, guardia de portalón, etc.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test6" value="SEGURIDAD"/>
                            <label for="test6"><b>Cumplir rol de seguridad o vigilancia:</b> Supervisor de seguridad, jefe de seguridad, inspector de depósito, control stacking, etc.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test7" value="LIMPIEZA"/>
                            <label for="test7"><b>Realizar labores de higiene, aseo, limpieza:</b> Trabajador de aseo, vigilante privado marítimo portuario.</label>
                        </div>
                        <div class="input-field col s12">
                            <input name="optFuncion" type="radio" id="test8" value="LABORES DE BODEGA, REFEER O PAÑOL"/>
                            <label for="test8"><b>Cumplir labores de bodega, refeer o pañol:</b> Encargado de bodega, encargado reefer, pañolero utilero de nave, frigorista, pañolero, bodeguero, etc.</label>
                        </div>
                    </div>
                    
                    <br/>
                    <p class="flow-text" align="justify">- Una vez ingresado el puerto, deberá ingresar la información de la/s empresa/s con la/s cual/es sostuvo alguna relación contractual.</p>
                    <p class="flow-text" align="justify">- Puede acceder a ese formulario de ingreso en el menú de navegación.</p>
                    <p class="flow-text">&nbsp;</p>
                    <br/>
                    <div class="row">
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoCinco();" value="ingresar">Ingresar<i class="material-icons right">send</i></button>
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="modificar" disabled="true">Modificar<i class="material-icons right">autorenew</i></button>
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="eliminar" disabled="true">Eliminar<i class="material-icons right">clear</i></button>
                    </div>

                    <br/>
                    <div class="row">
                        <a class="waves-effect waves-light btn" href="datosPuerto.do"><i class="material-icons left">backspace</i>Limpiar formulario</a>
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

                            function probandoCinco() {

                                var campoRut = document.getElementById("rut").value;
                                var indicePuerto = document.getElementById("cboPuerto").selectedIndex;
                                var meses = document.form.cboMesesTrabajados;
                                var opciones = meses.options;
                                var turnos = document.getElementById("turnos").value;
                                var indiceSeccion = document.getElementById("cboSeccion").selectedIndex;
                                var opcionesFuncion = document.getElementsByName("optFuncion");
                                var opcionesAccidente = document.getElementsByName("optAccidente");

                                var contador = -1;
                                for (i = 0; i < opciones.length; i++) {
                                    if (opciones[i].selected == true) {
                                        contador = contador + 1;
                                    }
                                }

                                var seleccionadoOtraOcupacion = false;
                                for (var i = 0; i < opcionesFuncion.length; i++) {
                                    if (opcionesFuncion[i].checked) {
                                        seleccionadoOtraOcupacion = true;
                                        break;
                                    }
                                }
                                
                                var seleccionadoAccidente = false;
                                for (var i = 0; i < opcionesAccidente.length; i++) {
                                    if (opcionesAccidente[i].checked) {
                                        seleccionadoAccidente = true;
                                        break;
                                    }
                                }

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
                                } else if (indicePuerto == null || indicePuerto == 0) {
                                    alert('[ERROR] Debe seleccionar un puerto en el campo 21');
                                    return false;
                                } else if (contador == 0) {
                                    alert('[ERROR] Debe seleccionar al menos un mes de trabajo en el campo 22');
                                    return false;
                                } else if (turnos == null || turnos.length == 0 || /^\s+$/.test(turnos)) {
                                    alert('[ERROR] El campo 23, que indica los turnos realizados, no puede estar vacío');
                                    return false;
                                } else if (isNaN(turnos)) {
                                    alert('[ERROR] Debe ingresar digitos en el campo 23');
                                    return false;
                                } else if (!seleccionadoAccidente) {
                                    alert('[ERROR] Debe indicar si ha sufrido algún accidente en el campo 24');
                                    return false;
                                } else if (indiceSeccion == null || indiceSeccion == 0) {
                                    alert('[ERROR] Debe seleccionar una sección en el campo 25');
                                    return false;
                                } else if (!seleccionadoOtraOcupacion) {
                                    alert('[ERROR] Debe seleccionar la función en el campo 26');
                                    return false;
                                } 
                                return true;
                            }

                            function probandoSeis() {

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
