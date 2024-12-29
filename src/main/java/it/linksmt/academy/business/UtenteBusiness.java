package it.linksmt.academy.business;

import it.linksmt.academy.dao.mysql.UtenteDAO;
import it.linksmt.academy.exceptions.LoginErratoException;
import it.linksmt.academy.model.Utente;

public class UtenteBusiness {

    private static UtenteBusiness instance;

    public static synchronized UtenteBusiness getInstance() {
        if (instance == null) instance = new UtenteBusiness();
        return instance;
    }

    private UtenteBusiness() {
        // empty constructor
    }

    public Utente login(String username, String password) throws LoginErratoException {
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.findByUsernameAndPassword(username, password);

        if (utente == null) throw new LoginErratoException();

        return utente;
    }
}
