package it.linksmt.academy.dao.mysql;

/*
 * Classe dedicata alla gestione del Database.
 * Gestisce l'apertura e la chiusura della connessione col Database
 * Fornisce i metodi per l'esecuzione delle query sul Database
 */

import lombok.Getter;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

@Log
public class DbConnection {

    @Getter
    private static Connection db;         // Connessione con il database

    private static boolean connesso;      // Flag indicante se la connessione e' attiva o meno
    private static DbConnection instance; // Istanza statica della classe DbConnection

    public static synchronized DbConnection getInstance() {
        if (instance == null) instance = new DbConnection();
        return instance;
    }

    private DbConnection() {
        if (!connesso) connetti();
    }

    // Apre la connessione con il Database
    private static void connetti() {
        connesso = false;
        try {
            // Carico il driver JDBC per la connessione con il database MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            db = DriverManager.getConnection("jdbc:mysql://127.0.0.1/" + System.getProperty("databaseSchema"), System.getProperty("databaseUser"), System.getProperty("databasePassword"));
            connesso = true;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Errore durante la connessione al db", e);
        }
    }

    // Esegue una query di selezione dati sul Database
    // query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
    // colonne: il numero di colonne da cui sara' composta la tupla del risultato
    // ritorna un ArrayList contenente tutte le tuple del risultato
    public ArrayList<String[]> eseguiQuery(String query) {
        ArrayList<String[]> v = null;
        String[] record;
        int colonne;
        try {
            Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
            ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
            v = new ArrayList<>();
            ResultSetMetaData rsmd = rs.getMetaData();
            colonne = rsmd.getColumnCount();

            while (rs.next()) {   // Creo il vettore risultato scorrendo tutto il ResultSet
                record = new String[colonne];
                for (int i = 0; i < colonne; i++) record[i] = rs.getString(i + 1);
                v.add(record.clone());
            }
            rs.close();     // Chiudo il ResultSet
            stmt.close();   // Chiudo lo Statement
        } catch (Exception e) {
            log.log(Level.SEVERE, "Errore durante l'esecuzione della query sul database", e);
        }

        return v;
    }

    // Esegue una query di aggiornamento sul Database
    // query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE da eseguire
    // ritorna TRUE se l'esecuzione e' andata a buon fine, FALSE se c'e' stata un'eccezione
    public boolean eseguiAggiornamento(String query) {
        boolean risultato;
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(query);
            risultato = true;
            stmt.close();
        } catch (Exception e) {
            risultato = false;
        }
        return risultato;
    }

    public void disconnetti() {
        try {
            db.close();    // Chiude la connessione con il Database
            connesso = false;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Errore durante la disconnessione dal database", e);
        }
    }
}
