package it.linksmt.academy.model;

import lombok.Data;

@Data
public class Ruolo {

    private int id;
    private String denominazione;

    public Ruolo(String denominazione) {
        this.denominazione = denominazione;
    }
}
