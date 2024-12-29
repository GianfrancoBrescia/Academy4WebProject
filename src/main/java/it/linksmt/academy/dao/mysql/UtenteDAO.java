package it.linksmt.academy.dao.mysql;

import it.linksmt.academy.dao.interfaces.IUtenteDAO;
import it.linksmt.academy.model.Fascia;
import it.linksmt.academy.model.Ruolo;
import it.linksmt.academy.model.Utente;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;

@Log
public class UtenteDAO implements IUtenteDAO {

    @Override
    public Utente findById(int id) {
        String query = "SELECT u.* FROM utente u WHERE u.Id = ?";
        RuoloDAO ruoloDAO = new RuoloDAO();
        FasciaDAO fasciaDAO = new FasciaDAO();

        DbConnection.getInstance();
        Connection db = DbConnection.getDb();
        try {
            PreparedStatement stm = db.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Ruolo ruolo = ruoloDAO.findById(rs.getInt(8));
                Fascia fascia = fasciaDAO.findById(rs.getInt(6));
                return new Utente(rs.getString(2), rs.getString(4), rs.getString(5),
                        fascia, rs.getString(7), ruolo);
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Errore durante il recupero delle informazioni dell'utente", e);
        }

        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {
        String query = "SELECT u.* FROM utente u";
        RuoloDAO ruoloDAO = new RuoloDAO();
        FasciaDAO fasciaDAO = new FasciaDAO();

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> {
                    Ruolo ruolo = ruoloDAO.findById(Integer.parseInt(x[7]));
                    Fascia fascia = fasciaDAO.findById(Integer.parseInt(x[5]));
                    return new Utente(x[1], x[3], x[4], fascia, x[6], ruolo);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Utente findByUsernameAndPassword(String username, String password) {
        String query = "SELECT u.* FROM utente u WHERE u.Username = '" + username + "' AND u.Password = '" + password + "'";
        RuoloDAO ruoloDAO = new RuoloDAO();
        FasciaDAO fasciaDAO = new FasciaDAO();

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> {
                    Ruolo ruolo = ruoloDAO.findById(Integer.parseInt(x[7]));
                    Fascia fascia = fasciaDAO.findById(Integer.parseInt(x[5]));
                    return new Utente(x[3], x[4], fascia, x[6], ruolo);
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public Utente findByMatricola(String matricola) {
        String query = "SELECT u.* FROM utente u WHERE u.Matricola = '" + matricola + "'";
        RuoloDAO ruoloDAO = new RuoloDAO();
        FasciaDAO fasciaDAO = new FasciaDAO();

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> {
                    Ruolo ruolo = ruoloDAO.findById(Integer.parseInt(x[7]));
                    Fascia fascia = fasciaDAO.findById(Integer.parseInt(x[5]));
                    return new Utente(x[1], x[3], x[4], fascia, ruolo);
                })
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Utente> findByIdFascia(int idFascia) {
        String query = "SELECT u.* FROM utente u WHERE u.IdFascia = " + idFascia;
        RuoloDAO ruoloDAO = new RuoloDAO();

        return Optional.ofNullable(DbConnection.getInstance().eseguiQuery(query)).orElse(new ArrayList<>()).stream()
                .map(x -> {
                    Ruolo ruolo = ruoloDAO.findById(Integer.parseInt(x[7]));
                    return new Utente(x[1], x[3], x[4], x[6], ruolo);
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
