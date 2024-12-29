<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.linksmt.academy.model.Utente"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area Privata</title>
    </head>
    <body>
        <%
            Utente u = (Utente)session.getAttribute("utenteLoggato");
            if (u == null) {
                response.sendRedirect("login.jsp");
            } else {
        %>
            <h2>Benvenuto, <%=u.getCognome()%> <%=u.getNome()%></h2>
        <% } %>
    </body>
</html>
