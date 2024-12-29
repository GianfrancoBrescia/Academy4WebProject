package it.linksmt.academy.dao.interfaces;

import it.linksmt.academy.model.Fascia;

import java.util.ArrayList;

public interface IFasciaDAO {

    Fascia findById(int id);

    ArrayList<Fascia> findAll();
}
