<%-- 
    Document   : index
    Created on : 21-07-2017, 11:04:30
    Author     : Horacio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </head>
    <body>
        <div class="container">
            <div class="row">
                <nav>
                    <div class="nav-wrapper red darken-1">
                        <a href="index.jsp" class="brand-logo">&nbsp;<img class="responsive-img" src="img/LOGO BLANCO SOL Y PORTUARIOS.png" width="120px" style="margin-top: 15px"></a>
                    </div>
                </nav>
            </div>

            <div class="row">
                <div class="col s10 offset-s1">
                    <h2><b>Formulario de Levantamiento</b></h2>
                    <p class="flow-text" align="justify">Este es un formulario de levantamiento de información de los trabajadores de la Unión Portuaria de Chile desarrollado por Fundación SOL.</p>
                </div>
            </div>

            <div class="col s12">
                <h4 class="center-align"><b>Ingreso</b></h4>
            </div>
            <div class="col s6 offset-s3">
                <div class="row">
                    <form class="col s8 offset-s2 card-panel z-depth-5"
                          action="login.do" method="post">
                        <div class="row">
                            <div class="input-field col s6">
                                <i class="material-icons prefix">account_circle</i>
                                <input id="icon_prefix" type="text" class="validate"
                                       name="txtUsuario" />
                                <label for="icon_prefix">Usuario</label>
                            </div>
                            <div class="input-field col s6">
                                <i class="material-icons prefix">lock_outline</i>
                                <input id="icon_pass" type="password" class="validate"
                                       name="txtPass">
                                <label for="icon_pass">Password</label>
                            </div>
                            <div class="center-align">    
                                <button class="btn waves-effect waves-light" type="submit" name="action">Enviar
                                    <i class="material-icons right">send</i>
                                </button>
                            </div>    
                        </div>
                    </form>

                </div>
                <div class="red-text center-align">
                    <p class="flow-text">${requestScope.msg}</p>
                </div>
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
        </div>
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>
        <script>
            $(function () {

                $(".button-collapse").sideNav();
            });
        </script>
    </body>
</html>
