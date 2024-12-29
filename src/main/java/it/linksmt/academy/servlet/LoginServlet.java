package it.linksmt.academy.servlet;

import it.linksmt.academy.business.UtenteBusiness;
import it.linksmt.academy.exceptions.LoginErratoException;
import it.linksmt.academy.model.Utente;
import lombok.extern.java.Log;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;


@WebServlet("/LoginServlet")
@Log
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Utente utente = UtenteBusiness.getInstance().login(username, password);
            HttpSession session = request.getSession();
            session.setAttribute("utenteLoggato", utente);
            response.sendRedirect("areaPrivata.jsp");
        } catch (LoginErratoException lee) {
            log.log(Level.SEVERE, "Errore durante la login dell'utente", lee);
            response.sendRedirect("login.jsp?errore=true");
        }
    }
}
