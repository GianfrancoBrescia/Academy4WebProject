package it.linksmt.academy.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
public class Appello {

    private int id;
    private Corso corso;
    private LocalDate data;
    private ArrayList<Utente> iscritti;
}
