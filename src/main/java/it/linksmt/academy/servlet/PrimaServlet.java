package it.linksmt.academy.servlet;

import it.linksmt.academy.dao.mysql.UtenteDAO;
import it.linksmt.academy.model.Utente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/PrimaServlet")
public class PrimaServlet extends HttpServlet {

    public PrimaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<Utente> docentiConPrimaFascia = utenteDAO.findByIdFascia(1);
		
		/* for(Docente d : docentiConPrimaFascia)
	    	  response.getWriter().append(d.toString() + " ");
	    */

        // redirect semplice
        // response.sendRedirect("docenti.jsp");

        // redirect con parametri
        request.setAttribute("docenti", docentiConPrimaFascia);
        request.getRequestDispatcher("docenti.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
