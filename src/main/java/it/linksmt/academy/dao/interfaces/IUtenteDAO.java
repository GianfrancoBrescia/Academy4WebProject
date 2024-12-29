package it.linksmt.academy.dao.interfaces;

import it.linksmt.academy.model.Utente;

import java.util.ArrayList;

public interface IUtenteDAO {

    Utente findById(int id);

    ArrayList<Utente> findAll();

    Utente findByUsernameAndPassword(String username, String password);

    Utente findByMatricola(String matricola);

    ArrayList<Utente> findByIdFascia(int idFascia);
}
