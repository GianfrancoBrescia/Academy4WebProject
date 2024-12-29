package it.linksmt.academy.model;

import lombok.Data;

@Data
public class Fascia {

    private int id;
    private String denominazione;

    public Fascia(String denominazione) {
        this.denominazione = denominazione;
    }
}
