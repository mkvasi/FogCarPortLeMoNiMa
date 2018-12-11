<%-- 
    Document   : LoginCreateUser
    Created on : 03-12-2018, 18:33:37
    Author     : nr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome page</title>
    </head>
    <body>
        <h1>Welcome to Sem 2</h1>

        <table>
            <tr><td>Login</td>
                <td>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="login">
                        Email:<br>
                        <input type="text" name="email" value="">
                        <br>
                        Password:<br>
                        <input type="password" name="password" value="">
                        <br>
                        <input type="submit" value="Login">
                    </form>
                </td>
                <td>Or Register</td>
                <td>
                    <form name="register" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="register">
                        Firstname:<br>
                        <input type="text" name="firstname" value="">
                        <br>
                        Lastname:<br>
                        <input type="text" name="lastname" value="">
                        <br>
                        Email:<br>
                        <input type="text" name="email" value="">
                        <br>
                        Zipcode:<br>
                        <input type="text" name="zipcode" value="">
                        <br>
                        City:<br>
                        <input type="text" name="city" value="">
                        <br>
                        Phone:<br>
                        <input type="text" name="phone" value="">
                        <br>
                        Password:<br>
                        <input type="password" name="password1" value="">
                        <br>
                        Retype Password:<br>
                        <input type="password" name="password2" value="">
                        <br>
                        <input type="submit" value="Register">
                    </form>
                </td>
            </tr>
            <tr><td>Login as employee</td>
                <td>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="command" value="backdoor">
                        <input type="submit" value="Backdoor">
                    </form>
                    <br><br>
        </table>
        
        
        <% String error = (String) request.getAttribute("error");
            if (error != null) {
                out.println("<H2>Error!!</h2>");
                out.println(error);
            }
        %>
    </body>
</html>