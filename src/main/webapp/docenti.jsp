<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="it.linksmt.academy.model.Utente"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Info Docenti</title>
    </head>
    <body>
        <%
        ArrayList<Utente> docenti = (ArrayList<Utente>)request.getAttribute("docenti");
        if (docenti != null && docenti.size() > 0) {
        %>
            <h2>Elenco dei docenti</h2>
            <table border="1">
                <tr>
                    <th>Nome</th>
                    <th>Cognome</th>
                </tr>
                <%
                for(Utente docente : docenti) {
                %>
                    <tr>
                        <td><%out.write(docente.getNome());%></td>
                        <td><%=docente.getCognome()%></td>
                    </tr>
                <% } %>
            </table>
        <%
        } else {
        %>
            <h2>Nessun docente di Prima Fascia presente sulla base dati</h2>
        <% } %>
    </body>
</html>
