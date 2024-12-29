package it.linksmt.academy.dao.mysql;

import it.linksmt.academy.dao.interfaces.IRuoloDAO;
import it.linksmt.academy.model.Ruolo;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class RuoloDAO implements IRuoloDAO {

    @Override
    public Ruolo findById(int id) {
        String query = "SELECT r.* FROM ruolo r WHERE r.Id = " + id;

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> new Ruolo(x[1])).findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Ruolo> findAll() {
        String query = "SELECT r.* FROM ruolo r";

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> new Ruolo(x[1]))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
