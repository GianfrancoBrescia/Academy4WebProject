package it.linksmt.academy.model;

import lombok.Data;

@Data
public class Corso {

    private int id;
    private String codice;
    private String nome;
    private Utente docente;
    private int cfu;
}
