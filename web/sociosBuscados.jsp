<%-- 
    Document   : socios
    Created on : 21-07-2017, 12:20:27
    Author     : Horacio
--%>

<%@page import="cl.modelo.SocioBuscado"%>
<%@page import="cl.modelo.Ingreso"%>
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
            function imprimir() {
                document.getElementById("rut").value = "";
            }

            function imprimirDos(rut) {
                alert(rut);
            }

            function limpiar_rutFamUno(rut) {
                var rutLimpio = rut;
                //Definimos los caracteres a eliminar
                var eliminar = ".-";
                //Los eliminamos
                for (var i = 0; i < eliminar.length; i++) {
                    rutLimpio = rutLimpio.replace(new RegExp("\\" + eliminar[i], 'gi'), '');
                }
                //Pasamos al campo el valor limpio
                document.getElementById("rutFamiliarUno").value = rutLimpio.toUpperCase();
            }

            function limpiar_rutFamDos(rut) {
                var rutLimpio = rut;
                //Definimos los caracteres a eliminar
                var eliminar = ".-";
                //Los eliminamos
                for (var i = 0; i < eliminar.length; i++) {
                    rutLimpio = rutLimpio.replace(new RegExp("\\" + eliminar[i], 'gi'), '');
                }
                //Pasamos al campo el valor limpio
                document.getElementById("rutFamiliarDos").value = rutLimpio.toUpperCase();
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

            function formato_rutFamUno(rut) {
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
                document.getElementById("rutFamiliarUno").value = sRut.toUpperCase();
            }

            function formato_rutFamDos(rut) {
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
                document.getElementById("rutFamiliarDos").value = sRut.toUpperCase();
            }

            function habilitarCombo(value) {
                alert(value);
                if (value == "AFP") {
                    document.getElementById("test25").checked = false;
                    document.getElementById("test25").disabled = true;
                } else {
                    document.getElementById("cboAfp").disabled = true;
                }
            }
            function habilitarAfp(form) {
                form.test25.checked = false;
                form.test25.disabled = true;
                form.test19.checked = false;
                form.test19.disabled = false;
                form.test20.checked = false;
                form.test20.disabled = false;
                form.test21.checked = false;
                form.test21.disabled = false;
                form.test22.checked = false;
                form.test22.disabled = false;
                form.test23.checked = false;
                form.test23.disabled = false;
                form.test24.checked = false;
                form.test24.disabled = false;
            }
            function deshabilitarAfp(form) {
                form.test25.checked = true;
                form.test25.disabled = false;
                form.test19.checked = false;
                form.test19.disabled = true;
                form.test20.checked = false;
                form.test20.disabled = true;
                form.test21.checked = false;
                form.test21.disabled = true;
                form.test22.checked = false;
                form.test22.disabled = true;
                form.test23.checked = false;
                form.test23.disabled = true;
                form.test24.checked = false;
                form.test24.disabled = true;
            }
            function noCotiza(form) {
                form.test17.disabled = true;
                form.test17.checked = false;
                form.test18.disabled = false;
                form.test18.checked = true;
            }
            function puedeCotizar(form) {
                form.test17.disabled = false;
                form.test17.checked = false;
                form.test18.disabled = false;
                form.test18.checked = false;
            }
        </script>
    </head>
    <%
        Ingreso ingreso = (Ingreso) session.getAttribute("ingreso");
        SocioBuscado socioBuscado = (SocioBuscado) request.getAttribute("socioBuscado");
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
                    <h2><b>Ingreso de Socios</b></h2>
                    <p class="flow-text" align="justify">Si obtiene los datos de un socio ya ingresado, puede modificar o eliminar los datos haciendo uso de los botones al final del formulario.</p>
                    <p class="flow-text" align="justify">Para modifcar los datos de un socio, deberá completar todos los campos nuevamente, exceptuando el rut.</p>
                </div>
            </div>

            <div class="row">
                <form class="col s10 offset-s1" action="socios.do?id_ingreso=${sessionScope.ingreso.id}" method="post">
                    <div class="col s12 card-panel red lighten-4">
                        <h4><b>Resultado de búsqueda</b></h4>
                        <br/>
                        <p class="flow-text"><b>Nombre: </b><%=socioBuscado.getNombre()%></p>
                        <p class="flow-text"><b>Rut: </b><%=socioBuscado.getRut_socio()%></p>
                        <p class="flow-text"><b>Sindicato: </b><%=socioBuscado.getSindicato()%></p>
                        <p class="flow-text"><b>Dirección: </b><%=socioBuscado.getDireccion()%></p>
                        <p class="flow-text"><b>Sexo: </b><%=socioBuscado.getSexo()%></p>
                        <p class="flow-text"><b>Edad: </b><%=socioBuscado.getEdad()%></p>
                        <p class="flow-text"><b>Fecha de Nacimiento (Año-Mes-Día): </b><%=socioBuscado.getFecha_nacimiento()%></p>
                        <p class="flow-text"><b>Comuna de residencia: </b><%=socioBuscado.getComuna_residencia()%></p>
                        <p class="flow-text"><b>Antigua comuna de residencia: </b><%=socioBuscado.getComuna_antigua()%></p>
                        <p class="flow-text"><b>Nacionalidad: </b><%=socioBuscado.getNacionalidad()%></p>
                        <p class="flow-text"><b>Educación: </b><%=socioBuscado.getEducacion()%></p>
                        <p class="flow-text"><b>Cantidad de miembros del hogar: </b><%=socioBuscado.getMiembros_hogar()%></p>
                        <p class="flow-text"><b>Es proveedor principal: </b><%=socioBuscado.getProveedor_principal()%></p>
                        <p class="flow-text"><b>Personas trabjando remuneradamente del hogar: </b><%=socioBuscado.getPersonas_trabajando()%></p>
                        <p class="flow-text"><b>Personas buscando trabajo del hogar: </b><%=socioBuscado.getPersonas_buscando()%></p>
                        <p class="flow-text"><b>La vivienda que habeta es : </b><%=socioBuscado.getVivienda()%></p>
                        <p class="flow-text"><b>Colaboración económica con otro hogar: </b><%=socioBuscado.getOtro_hogar()%></p>
                        <p class="flow-text"><b>Experiencia laboral portuaria en años: </b><%=socioBuscado.getExperiencia_años()%></p>
                        <p class="flow-text"><b>Posee otra ocupación: </b><%=socioBuscado.getOcupacion_aparte()%></p>
                        <p class="flow-text"><b>Actividad que reporta mayores ingresos mensuales: </b><%=socioBuscado.getActividad_principal()%></p>
                        <p class="flow-text"><b>Trabajo en más de un puerto: </b><%=socioBuscado.getMas_puerto()%></p>
                        <p class="flow-text"><b>Número de puertos en los cuales ha trabajado el último año: </b><%=socioBuscado.getNumero_puertos()%></p>
                        <p class="flow-text"><b>Familiares trabajando en el/los puerto/s: </b><%=socioBuscado.getFamilia_puertos()%></p>
                        <p class="flow-text"><b>Rut del primer familiar que trabaja en puerto: </b><%=socioBuscado.getRut_familiar_uno()%></p>
                        <p class="flow-text"><b>Rut del segundo familiar que trabaja en puerto: </b><%=socioBuscado.getRut_familiar_dos()%></p>
                        <p class="flow-text"><b>Afiliación: </b><%=socioBuscado.getAfiliado()%></p>
                        <p class="flow-text"><b>Afp: </b><%=socioBuscado.getAfp()%></p>
                        <p class="flow-text"><b>Cotiza: </b><%=socioBuscado.getCotizacion()%></p>
                        <p class="flow-text"><b>Afiliación a ISAPRE o FONASA: </b><%=socioBuscado.getIsapre()%></p>
                        <p class="flow-text"><b>Canal de información: </b><%=socioBuscado.getCanal_informacion()%></p>
                        <br/>
                    </div>
                    <br/>
                    <p class="flow-text">&nbsp;</p>
                    <br/>
                    <h4>IDENTIFICACIÓN DE SOCIO</h4>
                    <p class="flow-text">1. ¿Cuál es su nombre?</p>
                    <label>Nombre Completo</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtNombre" id="nombre" type="text" class="validate" maxlength="100">
                        </div>
                    </div>

                    </br>
                    <p class="flow-text">2. ¿Cuál es su rut? (no editable)</p>
                    <label>Rut</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtRut" id="rut" type="text" class="validate" value="<%=socioBuscado.getRut_socio()%>" maxlength="12" readonly="true">
                        </div>
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="buscar" disabled="true">Buscar<i class="material-icons right">find_in_page</i></button>
                    </div>

                    <br/>
                    <p class="flow-text">3. ¿A qué sindicato pertenece?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboSindicato" id="sindicato">
                                <option  value="NULO" disabled selected>SELECCIONE SINDICATO</option>
                                <c:forEach items="${requestScope.listaSindicato}" var="s">
                                    <option value="${s.codigo}">${s.nombre}</option>
                                </c:forEach>
                            </select>
                            <label for="sindicato">Sindicato</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">4. ¿Cuál es su dirección?</p>
                    <label>Dirección</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtDireccion" id="direccion" type="text" class="validate" maxlength="100">
                        </div>
                    </div>

                    <br/>
                    <h4>MÓDULO I: VARIABLES SOCIODEMOGRÁFICAS</h4>
                    <p class="flow-text">5. Sexo</p>
                    <label>Sexo</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optSexo" type="radio" id="test1" value="MUJER"/>
                            <label for="test1">Mujer</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optSexo" type="radio" id="test2" value="HOMBRE"/>
                            <label for="test2">Hombre</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">6.1. Edad</p>
                    <label>Edad</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtEdad" id="edad" type="text" class="validate">
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">6.2. Fecha de Nacimiento</p>
                    <label>Fecha de Nacimiento</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtFecha_Nacimiento" id="nacimiento" type="text" class="datepicker">
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">7. Comuna de residencia</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboComuna" id="comuna">
                                <option disabled selected>SELECCIONE COMUNA</option>
                                <c:forEach items="${requestScope.listaComuna}" var="c">
                                    <option value="${c.cut}">${c.nombre_comuna}</option>
                                </c:forEach>
                            </select>
                            <label for="comuna">Comuna</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">8. Comuna de residencia hace 5 años</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboComunaAntigua" id="comunaAntigua">
                                <option disabled selected>SELECCIONE COMUNA</option>
                                <option value="LA_MISMA">LA MISMA COMUNA</option>
                                <c:forEach items="${requestScope.listaComuna}" var="c">
                                    <option value="${c.cut}">${c.nombre_comuna}</option>
                                </c:forEach>
                            </select>
                            <label for="comunaAntigua">Comuna Antigua</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">9. Nacionalidad</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboNacionalidad" id="nacionalidad">
                                <option disabled selected>SELECCIONE NACIONALIDAD</option>
                                <option value="CHILENO/A">CHILENO/A</option>
                                <option value="PERUANO/A">PERUANO/A</option>
                                <option value="HAITIANO/A">HAITIANO/A</option>
                                <option value="COLOMBIANO/A">COLOMBIANO/A</option>
                                <option value="DOMINICANO/A">DOMINICANO/A</option>
                                <option value="ARGENTINO/A">ARGENTINO/A</option>
                                <option value="VENEZOLANO/A">VENEZOLANO/A</option>
                                <option value="BOLIVIANO/A">BOLIVIANO/A</option>
                                <c:forEach items="${requestScope.listaNacion}" var="n">
                                    <option value="${n.nombre}">${n.nombre}</option>
                                </c:forEach>
                            </select>
                            <label for="cantidad">Nacionalidad</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">10. Educación</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboEducacion" id="educacion">
                                <option disabled selected>SELECCIONE NIVEL EDUCATIVO</option>
                                <option value="NO TIENE">NO TIENE</option>
                                <option value="BÁSICA INCOMPLETA">BÁSICA INCOMPLETA</option>
                                <option value="BÁSICA COMPLETA">BÁSICA COMPLETA</option>
                                <option value="PRIMARIA O PREPARATORIA INCOMPLETA (SIST. ANTIGUO)">PRIMARIA O PREPARATORIA INCOMPLETA (SIST. ANTIGUO)</option>
                                <option value="PRIMARIA O PREPARATORIA COMPLETA (SIST. ANTIGUO)">PRIMARIA O PREPARATORIA COMPLETA (SIST. ANTIGUO)</option>
                                <option value="EDUCACIÓN MEDIA CIENTÍFICO HUMANISTA INCOMPLETA">EDUCACIÓN MEDIA CIENTÍFICO HUMANISTA INCOMPLETA</option>
                                <option value="EDUCACIÓN MEDIA CIENTÍFICO HUMANISTA COMPLETA">EDUCACIÓN MEDIA CIENTÍFICO HUMANISTA COMPLETA</option>
                                <option value="EDUCACIÓN MEDIA TÉCNICO PROFESIONAL INCOMPLETA">EDUCACIÓN MEDIA TÉCNICO PROFESIONAL INCOMPLETA</option>
                                <option value="EDUCACIÓN MEDIA TÉCNICO PROFESIONAL COMPLETA">EDUCACIÓN MEDIA TÉCNICO PROFESIONAL COMPLETA</option>
                                <option value="HUMANIDADES INCOMPLETA (SIST. ANTIGUO)">HUMANIDADES INCOMPLETA (SIST. ANTIGUO)</option>
                                <option value="HUMANIDADES COMPLETA (SIST. ANTIGUO)">HUMANIDADES COMPLETA (SIST. ANTIGUO)</option>
                                <option value="TÉCNICO NIVEL SUPERIOR INCOMPLETA (2 A 3 AÑOS)">TÉCNICO NIVEL SUPERIOR INCOMPLETA (2 A 3 AÑOS)</option>
                                <option value="TÉCNICO NIVEL SUPERIOR COMPLETA (2 A 3 AÑOS)">TÉCNICO NIVEL SUPERIOR COMPLETA (2 A 3 AÑOS)</option>
                                <option value="TÉCNICA, COMERCIAL, INDUSTRIAL O NORMALISTA INCOMPLETA (SIST. ANTIGUO)">TÉCNICA, COMERCIAL, INDUSTRIAL O NORMALISTA INCOMPLETA (SIST. ANTIGUO)</option>
                                <option value="TÉCNICA, COMERCIAL, INDUSTRIAL O NORMALISTA COMPLETA (SIST. ANTIGUO)">TÉCNICA, COMERCIAL, INDUSTRIAL O NORMALISTA COMPLETA (SIST. ANTIGUO)</option>
                                <option value="PROFESIONAL INCOMPLETA (4 AÑOS Y MÁS)">PROFESIONAL INCOMPLETA (4 AÑOS Y MÁS)</option>
                                <option value="PROFESIONAL COMPLETA (4 AÑOS Y MÁS)">PROFESIONAL COMPLETA (4 AÑOS Y MÁS)</option>
                                <option value="POSGRADO COMPLETA">POSGRADO COMPLETA</option>
                                <option value="POSGRADO INCOMPLETA">POSGRADO INCOMPLETA</option>
                            </select>
                            <label for="educacion">Educación</label>
                        </div>
                    </div>

                    <br/>
                    <h4>MÓDULO II: INFORMACIÓN BÁSICA DEL HOGAR</h4>
                    <p class="flow-text">11. ¿Cuantas personas componen su hogar? (incluído usted)</p>
                    <label for="miembrosHogar">*Considere todas aquellas personas que residen habitualmente en la vivienda ocupada por el hogar y comparten un presupuesto común para alimentación y servicios básicos.</label><br/>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboMiembrosHogar" id="miembrosHogar">
                                <option disabled selected>SELECCIONE NÚMERO</option>
                                <% for (int i = 1; i <= 30; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">12. ¿Es usted el/la proveedor/a principal del hogar?</p>
                    <label>Proveedor principal</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optProveedor" type="radio" id="test4" value="1"/>
                            <label for="test4">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optProveedor" type="radio" id="test5" value="0"/>
                            <label for="test5">No</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">13. ¿Cuántas personas del hogar se encuentran trabajando remuneradamente?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboTrabajandoRemuneradamente" id="trabajoRemunerado">
                                <option disabled selected>SELECCIONE NÚMERO</option>
                                <% for (int i = 1; i <= 30; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <%}%>
                            </select>
                            <label for="trabajoRemunerado">Miembros trabajando</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">14. ¿Cuántas personas del hogar se encuentran buscando trabajo de forma activa?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboBuscandoTrabajo" id="buscandoTrabajo">
                                <option disabled selected>SELECCIONE NÚMERO</option>
                                <% for (int i = 0; i <= 30; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <%}%>
                            </select>
                            <label for="buscandoTrabajo">Miembros buscando trabajando</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">15. ¿La vivienda que habita es...?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboVivienda" id="vivienda">
                                <option disabled selected>SELECCIONE TIPO DE VIVIENDA</option>
                                <option value="PROPIA, PAGÁNDOSE">PROPIA, PAGÁNDOSE</option>
                                <option value="PROPIA TOTALMENTE PAGADA">PROPIA TOTALMENTE PAGADA</option>
                                <option value="ARRENDADA">ARRENDADA</option>
                                <option value="CEDIDA">CEDIDA</option>
                                <option value="TOMA O CAMPAMENTO">TOMA O CAMPAMENTO</option>
                                <option value="ALLEGADO">ALLEGADO</option>
                            </select>
                            <label for="vivienda">Vivienda</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">16. ¿Colabora económicamente con otra persona, (familiar o no) que no pertenezca a ese hogar?</p>
                    <label>Otro hogar</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optOtroHogar" type="radio" id="test6" value="1"/>
                            <label for="test6">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optOtroHogar" type="radio" id="test7" value="0"/>
                            <label for="test7">No</label>
                        </div>
                    </div>

                    <br/>
                    <h4>MÓDULO III: INFORMACIÓN LABORAL</h4>
                    <p class="flow-text">17. ¿Cuántos años de experiencia tiene trabajando en el sector portuario?</p>
                    <p class="flow-text">- Años</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboAniosExperiencia" id="experienciaAnios">
                                <option value="0">0</option>
                                <% for (int i = 1; i <= 60; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <%}%>
                            </select>
                            <label for="experienciaAnios">Años</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">18. ¿Tiene otra ocupación aparte del trabajo portuario?</p>
                    <label>Otra ocupación</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optOtraOcupacion" type="radio" id="test8" onClick="habilita(this.form)" value="1"/>
                            <label for="test8">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optOtraOcupacion" type="radio" id="test9" onClick="deshabilita(this.form)" value="0"/>
                            <label for="test9">No</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">18.1. ¿Cuál de ellas le reporta mayores ingresos?</p>
                    <label>Mayores ingresos</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optMayoresIngresos" type="radio" id="test10" value="PORTUARIA"/>
                            <label for="test10">Portuaria</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optMayoresIngresos" type="radio" id="test11" value="OTRA"/>
                            <label for="test11">Otra</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">19. ¿Trabaja en más de un puerto?</p>
                    <label>Trabajo en más de un puerto</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optMasPuerto" type="radio" id="test12" value="1"/>
                            <label for="test12">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optMasPuerto" type="radio" id="test13" value="0"/>
                            <label for="test13">No</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">19.1. ¿En cuántos puertos trabajó el último año?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboNumeroPuertos" id="numeroPuertos">
                                <option disabled selected>SELECCIONE NÚMERO</option>
                                <% for (int i = 1; i <= 30; i++) {%>
                                <option value="<%= i%>"><%= i%></option>
                                <%}%>
                            </select>
                            <label for="numeroPuertos">Número de puertos</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">20. ¿Alguno de los miembros de su hogar trabaja en alguno de estos puertos?</p>
                    <label>Familiares en puertos</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="familiarPuerto" type="radio" id="test888" onClick="habilitaRut(this.form)" value="1"/>
                            <label for="test888">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="familiarPuerto" type="radio" id="test777" onClick="deshabilitaRut(this.form)" value="0"/>
                            <label for="test777">No</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">20.1. RUT de familiar</p>
                    <label>Rut</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtRutFamiliarUno" id="rutFamiliarUno" type="text" class="validate" maxlength="12" disabled="true" placeholder="Ingrese el rut sin puntos (.) ni guión (-)" onblur="limpiar_rutFamUno(this.value);
                                    formato_rutFamUno(this.value);">
                        </div>
                    </div>
                    <p class="flow-text">20.2. RUT de familiar</p>
                    <label>Rut</label>
                    <div class="row">
                        <div class="input-field col s12">
                            <input name="txtRutFamiliarDos" id="rutFamiliarDos" type="text" class="validate" maxlength="12" disabled="true" placeholder="Ingrese el rut sin puntos (.) ni guión (-)" onblur="limpiar_rutFamDos(this.value);
                                    formato_rutFamDos(this.value);">
                        </div>
                    </div>

                    <p class="flow-text" align="justify">* Las preguntas 21 a la 26, correspondientes al Módulo de 'Trabajo en Puerto', se encuentran en la página 'Ingreso de trabajo en puerto' (Menú de navegación) de esta plataforma. Las preguntas 27 y 28, relativas a la(s) empresa(s) en que se desempeña el trabajador, se encuentra en la página 'Ingreso de empresas' (Menú de navegación) de esta plataforma.</p>

                    <br/>
                    <h4>MÓDULO V: INFORMACIÓN PREVISIONAL BÁSICA</h4>
                    <p class="flow-text">29. ¿Está afiliado a algún sistema de previsión?</p>
                    <label>Afiliado</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optAfiliado" type="radio" id="test14" value="AFP" onclick="habilitarAfp(this.form);
                                    puedeCotizar(this.form);"/>
                            <label for="test14">Sí, AFP</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optAfiliado" type="radio" id="test15" value="INP" onclick="deshabilitarAfp(this.form);
                                    puedeCotizar(this.form);"/>
                            <label for="test15">Sí, Caja Portuaria (INP)</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optAfiliado" type="radio" id="test16" value="NO AFILIADO" onclick="deshabilitarAfp(this.form);
                                    noCotiza(this.form);"/>
                            <label for="test16">No está Afiliado</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">29.1. ¿A cuál AFP?</p>
                    <div class="row">
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test19" value="HABITAT"/>
                            <label for="test19">HABITAT</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test20" value="CUPRUM"/>
                            <label for="test20">CUPRUM</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test21" value="MODELO"/>
                            <label for="test21">MODELO</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test22" value="CAPITAL"/>
                            <label for="test22">CAPITAL</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test23" value="PLAN VITAL"/>
                            <label for="test23">PLAN VITAL</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test24" value="PROVIDA"/>
                            <label for="test24">PROVIDA</label>
                        </div>
                        <div class="input-field col s4">
                            <input name="optAfp" type="radio" id="test25" value="NO COTIZA EN AFP"/>
                            <label for="test25">NO COTIZA EN AFP</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">30. ¿Cotiza actualmente?</p>
                    <label>Cotización</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optCotiza" type="radio" id="test17" value="1"/>
                            <label for="test17">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optCotiza" type="radio" id="test18" value="0"/>
                            <label for="test18">No</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text">31. ¿Esta afiliado a alguna ISAPRE o FONASA?</p>
                    <label>Cotización</label>
                    <div class="row">
                        <div class="input-field col s6">
                            <input name="optIsapre" type="radio" id="test450" value="1"/>
                            <label for="test450">Sí</label>
                        </div>
                        <div class="input-field col s6">
                            <input name="optIsapre" type="radio" id="test500" value="0"/>
                            <label for="test500">No</label>
                        </div>
                    </div>

                    <br/>
                    <h4>HÁBITOS DE INFORMACIÓN</h4>
                    <p class="flow-text">32. ¿Qué canal de información utiliza para la actividad sindical?</p>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="cboCanal" id="canalInformacion">
                                <option disabled selected>SELECCIONE MEDIO</option>
                                <option value="DIARIOS MURALES">DIARIOS MURALES</option>
                                <option value="FOLLETOS">FOLLETOS</option>
                                <option value="FACEBOOK UNIÓN PORTUARIA">FACEBOOK UNIÓN PORTUARIA</option>
                                <option value="SITIO UNIONPORTUARIA.CL">SITIO UNIONPORTUARIA.CL</option>
                                <option value="SITIO PORTALPORTUARIO.CL">SITIO PORTALPORTUARIO.CL</option>
                                <option value="WHATS APP">WHATS APP</option>
                            </select>
                            <label for="canaInformacion">Canal de información</label>
                        </div>
                    </div>

                    <br/>
                    <p class="flow-text" align="justify">- A continuación deberá ingresar la información de todos los puertos en donde trabaja.</p>
                    <p class="flow-text" align="justify">- Antes debe ingresar la información del socio, presionando 'Ingresar' o 'Modificar' según corresponda.</p>
                    <p class="flow-text" align="justify">- Si selecciona 'Eliminar', se eliminará la información del socio además de toda la información de los puertos asociada.</p>
                    <br/>
                    <div class="row">
                        <button class="btn waves-effect waves-light" type="submit" name="action" value="ingresar" disabled="true">Ingresar<i class="material-icons right">send</i></button>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoTres();" value="modificar">Modificar<i class="material-icons right">autorenew</i></button>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="return probandoCuatro();" value="eliminar">Eliminar<i class="material-icons right">clear</i></button>
                    </div>

                    <br/>
                    <div class="row">
                        <a class="waves-effect waves-light btn" href="datosSocio.do"><i class="material-icons left">backspace</i>Limpiar formulario</a>
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
                            $('.datepicker').pickadate({
                                selectMonths: true, // Creates a dropdown to control month
                                selectYears: 50, // Creates a dropdown of 15 years to control year,
                                today: 'Hoy',
                                clear: 'Limpiar',
                                close: 'Ok',
                                closeOnSelect: false // Close upon selecting a date,
                            });
                            function habilita(form) {
                                form.test10.checked = false;
                                form.test11.disabled = false;
                            }
                            function deshabilita(form) {
                                form.test10.checked = true;
                                form.test11.disabled = true;
                            }

                            function habilitaRut(form) {
                                form.rutFamiliarUno.disabled = false;
                                form.rutFamiliarDos.disabled = false;
                            }
                            function deshabilitaRut(form) {
                                form.rutFamiliarUno.disabled = true;
                                form.rutFamiliarDos.disabled = true;
                                form.rutFamiliarUno.value = "";
                                form.rutFamiliarDos.value = "";
                            }

                            function probandoTres() {

                                var campoNombre = document.getElementById("nombre").value;
                                var campoRut = document.getElementById("rut").value;
                                var indiceSindicato = document.getElementById("sindicato").selectedIndex;
                                var direccion = document.getElementById("direccion").value;
                                var opcionesSexo = document.getElementsByName("optSexo");
                                var edad = document.getElementById("edad").value;
                                var nacimiento = document.getElementById("nacimiento").value;
                                var indiceComuna = document.getElementById("comuna").selectedIndex;
                                var indiceComunaAntigua = document.getElementById("comunaAntigua").selectedIndex;
                                var indiceNacionalidad = document.getElementById("nacionalidad").selectedIndex;
                                var indiceEducacion = document.getElementById("educacion").selectedIndex;
                                var indiceMiembrosHogar = document.getElementById("miembrosHogar").selectedIndex;
                                var opcionesProveedor = document.getElementsByName("optProveedor");
                                var indiceTrabajoRemunerado = document.getElementById("trabajoRemunerado").selectedIndex;
                                var indiceBuscandoTrabajo = document.getElementById("buscandoTrabajo").selectedIndex;
                                var miembros = document.getElementById("miembrosHogar").value;
                                var remunerado = document.getElementById("trabajoRemunerado").value;
                                var buscando = document.getElementById("buscandoTrabajo").value;
                                var indiceVivienda = document.getElementById("vivienda").selectedIndex;
                                var opcionesOtroHogar = document.getElementsByName("optOtroHogar");
                                var indiceAnios = document.getElementById("experienciaAnios").selectedIndex;
                                var opcionesOtraOcupacion = document.getElementsByName("optOtraOcupacion");
                                var opcionesMayoresIngresos = document.getElementsByName("optMayoresIngresos");
                                var opcionesMasPuertos = document.getElementsByName("optMasPuerto");
                                var indiceNroPuertos = document.getElementById("numeroPuertos").selectedIndex;
                                var opcionesFamiliares = document.getElementsByName("familiarPuerto");
                                var campoRutFamUno = document.getElementById("rutFamiliarUno").value;
                                var campoRutFamDos = document.getElementById("rutFamiliarDos").value;
                                var opcionesAfiliado = document.getElementsByName("optAfiliado");
                                var opcionesAfp = document.getElementsByName("optAfp");
                                var opcionesCotiza = document.getElementsByName("optCotiza");
                                var opcionesIsapre = document.getElementsByName("optIsapre");
                                var indiceCanalInformacion = document.getElementById("canalInformacion").selectedIndex;

                                var seleccionadoFamiliaPuerto = false;
                                for (var i = 0; i < opcionesFamiliares.length; i++) {
                                    if (opcionesFamiliares[i].checked) {
                                        seleccionadoFamiliaPuerto = true;
                                        break;
                                    }
                                }

                                if (seleccionadoFamiliaPuerto) {
                                    for (var i = 0; i < opcionesFamiliares.length; i++) {
                                        if (opcionesFamiliares[i].checked && i === 0) {
                                            var opcionFamilia = 1;
                                        }
                                    }
                                }

                                var seleccionadoSexo = false;
                                for (var i = 0; i < opcionesSexo.length; i++) {
                                    if (opcionesSexo[i].checked) {
                                        seleccionadoSexo = true;
                                        break;
                                    }
                                }

                                var seleccionadoProveedor = false;
                                for (var i = 0; i < opcionesProveedor.length; i++) {
                                    if (opcionesProveedor[i].checked) {
                                        seleccionadoProveedor = true;
                                        break;
                                    }
                                }

                                var numeroMiembros = parseInt(miembros);
                                var numeroRemunerado = parseInt(remunerado);
                                var numeroBuscando = parseInt(buscando);
                                var confirmacionMiembrosHogar = true;
                                if (numeroMiembros >= numeroRemunerado && numeroMiembros >= numeroBuscando) {
                                    confirmacionMiembrosHogar = true;
                                } else {
                                    confirmacionMiembrosHogar = false;
                                }

                                var seleccionadoOtroHogar = false;
                                for (var i = 0; i < opcionesOtroHogar.length; i++) {
                                    if (opcionesOtroHogar[i].checked) {
                                        seleccionadoOtroHogar = true;
                                        break;
                                    }
                                }

                                var seleccionadoOtraOcupacion = false;
                                for (var i = 0; i < opcionesOtraOcupacion.length; i++) {
                                    if (opcionesOtraOcupacion[i].checked) {
                                        seleccionadoOtraOcupacion = true;
                                        break;
                                    }
                                }

                                var seleccionadoMayoresIngresos = false;
                                for (var i = 0; i < opcionesMayoresIngresos.length; i++) {
                                    if (opcionesMayoresIngresos[i].checked) {
                                        seleccionadoMayoresIngresos = true;
                                        break;
                                    }
                                }

                                var seleccionadoMasPuerto = false;
                                for (var i = 0; i < opcionesMasPuertos.length; i++) {
                                    if (opcionesMasPuertos[i].checked) {
                                        seleccionadoMasPuerto = true;
                                        break;
                                    }
                                }

                                //*********
                                var opcionMasPuerto = false;
                                if (seleccionadoMasPuerto) {
                                    for (var i = 0; i < opcionesMasPuertos.length; i++) {
                                        if (opcionesMasPuertos[i].checked && i === 0) {
                                            var opcionMasPuerto = true;
                                        }
                                    }
                                }

                                var seleccionadoAfiliado = false;
                                for (var i = 0; i < opcionesAfiliado.length; i++) {
                                    if (opcionesAfiliado[i].checked) {
                                        seleccionadoAfiliado = true;
                                        break;
                                    }
                                }

                                var seleccionadoAfp = false;
                                for (var i = 0; i < opcionesAfp.length; i++) {
                                    if (opcionesAfp[i].checked) {
                                        seleccionadoAfp = true;
                                        break;
                                    }
                                }

                                var seleccionadoCotiza = false;
                                for (var i = 0; i < opcionesCotiza.length; i++) {
                                    if (opcionesCotiza[i].checked) {
                                        seleccionadoCotiza = true;
                                        break;
                                    }
                                }

                                var seleccionadoIsapre = false;
                                for (var i = 0; i < opcionesIsapre.length; i++) {
                                    if (opcionesIsapre[i].checked) {
                                        seleccionadoIsapre = true;
                                        break;
                                    }
                                }

                                if (campoNombre == null || campoNombre.length == 0 || /^\s+$/.test(campoNombre)) {
                                    alert('[ERROR] El campo 1, que indica el nombre del trabajador, no puede estar vacío');
                                    return false;
                                } else if (campoNombre.length > 100) {
                                    alert('[ERROR] El campo 1, que indica el nombre del trabajador, no puede tener más de 100 caracteres');
                                    return false;
                                } else if (campoRut == null || campoRut.length == 0 || /^\s+$/.test(campoRut)) {
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
                                } else if (indiceSindicato == null || indiceSindicato == 0) {
                                    alert('[ERROR] Debe seleccionar un sindicato en el campo 3');
                                    return false;
                                } else if (direccion.length < 1) {
                                    alert('[ERROR] Debe indicar una dirección en el campo 4');
                                    return false;
                                } else if (direccion.length > 100) {
                                    alert('[ERROR] La dirección debe contener menos de 100 caracteres en el campo 4');
                                    return false;
                                } else if (!seleccionadoSexo) {
                                    alert('[ERROR] Debe seleccionar el sexo en el campo 5');
                                    return false;
                                } else if (edad == null || edad.length == 0 || /^\s+$/.test(edad)) {
                                    alert('[ERROR] El campo 6.1, que indica la edad del trabajador, no puede estar vacío');
                                    return false;
                                } else if (isNaN(edad)) {
                                    alert('[ERROR] Debe ingresar digitos en el campo de edad 6.1');
                                    return false;
                                } else if (edad > 100) {
                                    alert('[ERROR] Debe ingresar una cifra menor a 100 años en el campo 6.1');
                                    return false;
                                } else if (edad < 15) {
                                    alert('[ERROR] Debe ingresar una cifra mayor a 15 años en el campo 6.1');
                                    return false;
                                } else if (nacimiento == null || nacimiento.length == 0 || /^\s+$/.test(nacimiento)) {
                                    alert('[ERROR] El campo 6.2, que indica la fecha de nacimiento del trabajador, no puede estar vacío');
                                    return false;
                                } else if (indiceComuna == null || indiceComuna == 0) {
                                    alert('[ERROR] Debe seleccionar una comuna en el campo 7');
                                    return false;
                                } else if (indiceComunaAntigua == null || indiceComunaAntigua == 0) {
                                    alert('[ERROR] Debe seleccionar una comuna antigua en el campo 8');
                                    return false;
                                } else if (indiceNacionalidad == null || indiceNacionalidad == 0) {
                                    alert('[ERROR] Debe seleccionar una nacionalidad en el campo 9');
                                    return false;
                                } else if (indiceEducacion == null || indiceEducacion == 0) {
                                    alert('[ERROR] Debe seleccionar un nivel educativo en el campo 10');
                                    return false;
                                } else if (indiceMiembrosHogar == null || indiceMiembrosHogar == 0) {
                                    alert('[ERROR] Debe seleccionar un número de miembros del hogar en el campo 11');
                                    return false;
                                } else if (!seleccionadoProveedor) {
                                    alert('[ERROR] Debe indicar si es el proveedor principal en el campo 12');
                                    return false;
                                } else if (indiceTrabajoRemunerado == null || indiceTrabajoRemunerado == 0) {
                                    alert('[ERROR] Debe seleccionar el número de integrantes de la familia que se encuentran trabajando remuneradamente en el campo 13');
                                    return false;
                                } else if (indiceBuscandoTrabajo == null || indiceBuscandoTrabajo == 0) {
                                    alert('[ERROR] Debe seleccionar el número de integrantes de la familia que se encuentran buscando trabajo en el campo 14');
                                    return false;
                                } else if (!confirmacionMiembrosHogar) {
                                    alert('[ERROR] El número de integrantes de la familia que se encuentra trabajando remuneradamente, o buscando trabajo, no puede ser superior al número total de integrantes de dicha familia');
                                    return false;
                                } else if (indiceVivienda == null || indiceVivienda == 0) {
                                    alert('[ERROR] Debe indicar el tipo de relación con la vivienda en donde habita en el campo 15');
                                    return false;
                                } else if (!seleccionadoOtroHogar) {
                                    alert('[ERROR] Debe indicar si colabora con otro hogar en el campo 16');
                                    return false;
                                } else if (indiceAnios == null || indiceAnios == 0) {
                                    alert('[ERROR] Debe indicar sus años de experiencia en el campo 17');
                                    return false;
                                } else if (!seleccionadoOtraOcupacion) {
                                    alert('[ERROR] Debe indicar si posee otra ocupación en el campo 18');
                                    return false;
                                } else if (!seleccionadoMayoresIngresos) {
                                    alert('[ERROR] Debe indicar si el trabajo portuario, u otro, es el que reporta mayores ingresos en el campo 18.1');
                                    return false;
                                } else if (!seleccionadoMasPuerto) {
                                    alert('[ERROR] Debe indicar si trabaja o no en más de un puerto en el campo 19');
                                    return false;
                                } else if (indiceNroPuertos == null || indiceNroPuertos == 0) {
                                    alert('[ERROR] Debe indicar la cantidad de puertos donde ha trabajado en el último año en el campo 19.1');
                                    return false;
                                } else if (opcionMasPuerto && indiceNroPuertos < 2) {
                                    alert('[ERROR] Ha declarado trabajar en más de un puerto, no puede indicar un número menor a dos en el campo 19.1');
                                    return false;
                                } else if (!opcionMasPuerto && indiceNroPuertos > 1) {
                                    alert('[ERROR] Ha declarado trabajar en un puerto, no puede indicar un número mayor a uno en el campo 19.1');
                                    return false;
                                } else if (!seleccionadoFamiliaPuerto) {
                                    alert('[ERROR] Debe indicar si posee algún familiar que haya trabajado en puertos el campo 20');
                                    return false;
                                } else if (opcionFamilia === 1 && campoRutFamUno.length === 0) {
                                    alert('[ERROR] El campo 20.1, que indica el rut del primer familiar, no puede estar vacío');
                                    return false;
                                } else if (opcionFamilia === 1 && campoRutFamUno.length < 11) {
                                    alert('[ERROR] El campo 20.1, que indica el rut del primer familiar, no puede tener menos de 11 caracteres');
                                    return false;
                                } else if (opcionFamilia === 1 && campoRutFamUno.length > 12) {
                                    alert('[ERROR] El campo 20.1, que indica el rut del primer familiar, no puede tener más de 12 caracteres');
                                    return false;
                                } else if (opcionFamilia === 1 && valrut(campoRutFamUno) == false) {
                                    alert('[ERROR] El rut ingresado en el campo 20.1 no es válido');
                                    return false;
                                } else if (opcionFamilia === 1 && campoRutFamDos.length > 0 && campoRutFamDos.length < 11) {
                                    alert('[ERROR] El campo 20.2, que indica el rut del segundo familiar, no puede tener menos de 11 caracteres');
                                    return false;
                                } else if (opcionFamilia === 1 && campoRutFamDos.length > 12) {
                                    alert('[ERROR] El campo 20.2, que indica el rut del segundo familiar, no puede tener más de 12 caracteres');
                                    return false;
                                } else if (opcionFamilia === 1 && campoRutFamDos.length > 0 && valrut(campoRutFamDos) == false) {
                                    alert('[ERROR] El rut ingresado en el campo 20.2 no es válido');
                                    return false;
                                } else if (!seleccionadoAfiliado) {
                                    alert('[ERROR] Debe indicar si está afiliado en el campo 29');
                                    return false;
                                } else if (!seleccionadoAfp) {
                                    alert('[ERROR] Debe indicar a que AFP está afiliado en el campo 29.1');
                                    return false;
                                } else if (!seleccionadoCotiza) {
                                    alert('[ERROR] Debe indicar si cotiza o no en el campo 30');
                                    return false;
                                } else if (!seleccionadoIsapre) {
                                    alert('[ERROR] Debe indicar si está afiliado a alguna ISAPRE en el campo 31');
                                    return false;
                                } else if (indiceCanalInformacion == null || indiceCanalInformacion == 0) {
                                    alert('[ERROR] Debe seleccionar un canal de información en el campo 32');
                                    return false;
                                }

                                return true;
                            }

                            function probandoCuatro() {

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
