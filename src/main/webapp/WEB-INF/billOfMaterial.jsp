<%@page import="FunctionLayer.BillOfMaterial"%>
<%@page import="java.util.HashMap"%>
<%@page import="FunctionLayer.Carport"%>
<%@page import="FunctionLayer.Material"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <title>Fog - carport beregner</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- styles -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,400,600,700" rel="stylesheet">
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
        <link href="bootstrap/css/style.css" rel="stylesheet">
        <link href="bootstrap/color/default.css" rel="stylesheet">



        <!-- fav and touch icons -->

        <link rel="shortcut icon" href="bootstrap/ico/favicon.ico">

        <!-- =======================================================
          Theme Name: Serenity
          Theme URL: https://bootstrapmade.com/serenity-bootstrap-corporate-template/
          Author: BootstrapMade.com
          Author URL: https://bootstrapmade.com
        ======================================================= -->
    </head>
        <% Carport carport = (Carport) request.getAttribute("carport");%>
        <% HashMap<String,Material> billofmaterial =(HashMap) request.getAttribute("billOfMaterial");  %>
        <% double offerPrice = (Double) request.getAttribute("offerprice");%>
    <body data-spy="scroll" data-target=".bs-docs-sidebar">
        <header>
            <!-- Navbar
            ================================================== -->
            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">
                        <!-- logo -->
                        <a class="brand logo" href="index.html">
                            <img src="bootstrap/img/foglogo.PNG" alt="" />
                        </a>
                        <!-- end logo -->
                        <!-- top menu -->
                   
                        <!-- end menu -->
                    </div>
                </div>
            </div>
        </header>
        <!-- Subhead
      ================================================== -->
        <section id="subintro">
            <div class="jumbotron subhead" id="overview">
                <div class="container">
                    <div class="row">
                        <div class="span12">
                            <div class="centered">
                                <h3>Din carport er nedenfor</h3>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


      

       

             <div id="middle">
            <table>
                <thead>
                    <tr>
                        <th>Anvendes som</th>
                        <th>Produkt</th>
                        <th>Længde</th>
                        <th>Antal</th>
                    
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%out.print(billofmaterial.get("Spær").getName());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Spær").getQty());%></td>
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Rem").getName());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getDescription());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getLength());%></td>
                        <td><%out.print(billofmaterial.get("Rem").getQty());%></td>
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getName());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getDescription());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag højre").getQty());%></td>
   
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getName());%></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getDescription());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getQty());%></td>
   
                    </tr>
                    <tr>
                        <td><%out.print(billofmaterial.get("Stolper").getName());%></td>
                        <td><%out.print(billofmaterial.get("Stolper").getDescription());%></td>
                        <td></td>
                        <td><%out.print(billofmaterial.get("Universalbeslag venstre").getQty());%></td>
   
                    </tr>
                </tbody>
        </div>
                        
           



            
            </table>

        </div> 
                     




     
        <!-- Footer
       ================================================== -->
        <footer class="footer">


            <div class="container">
                <div class="row">
                    <div class="span6">
                        <p>
                            &copy; Serenity - All right reserved
                        </p>
                    </div>
                    <div class="span6">
                        <div class="credits">
                            <!--
                              All the links in the footer should remain intact.
                              You can delete the links only if you purchased the pro version.
                              Licensing information: https://bootstrapmade.com/license/
                              Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Serenity
                            -->
                            Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                        </div>
                    </div>
                </div>
            </div>

        </footer>




    </body>

</html>