package it.linksmt.academy.dao.interfaces;

import it.linksmt.academy.model.Ruolo;

import java.util.ArrayList;

public interface IRuoloDAO {

    Ruolo findById(int id);

    ArrayList<Ruolo> findAll();
}
