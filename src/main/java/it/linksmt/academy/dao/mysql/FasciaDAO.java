package it.linksmt.academy.dao.mysql;

import it.linksmt.academy.dao.interfaces.IFasciaDAO;
import it.linksmt.academy.model.Fascia;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class FasciaDAO implements IFasciaDAO {

    @Override
    public Fascia findById(int id) {
        String query = "SELECT f.* FROM fascia f WHERE f.Id = " + id;

        return DbConnection.getInstance().eseguiQuery(query).stream()
                .map(x -> new Fascia(x[1])).findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Fascia> findAll() {
        String query = "SELECT f.* FROM fascia f";

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> new Fascia(x[1]))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
