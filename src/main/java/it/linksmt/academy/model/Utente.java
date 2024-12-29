package it.linksmt.academy.model;

import lombok.Data;

@Data
public class Utente {

    private int id;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private Fascia fascia;
    private String matricola;
    private Ruolo ruolo;

    public Utente(String nome, String cognome, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public Utente(String username, String nome, String cognome, Fascia fascia, String matricola, Ruolo ruolo) {
        this(nome, cognome, ruolo);
        this.username = username;
        this.fascia = fascia;
        this.matricola = matricola;
    }

    public Utente(String nome, String cognome, Fascia fascia, String matricola, Ruolo ruolo) {
        this(nome, cognome, ruolo);
        this.fascia = fascia;
        this.matricola = matricola;
    }

    public Utente(String username, String nome, String cognome, Fascia fascia, Ruolo ruolo) {
        this(nome, cognome, ruolo);
        this.username = username;
        this.fascia = fascia;
    }

    public Utente(String username, String nome, String cognome, String matricola, Ruolo ruolo) {
        this(nome, cognome, ruolo);
        this.username = username;
        this.matricola = matricola;
    }
}
