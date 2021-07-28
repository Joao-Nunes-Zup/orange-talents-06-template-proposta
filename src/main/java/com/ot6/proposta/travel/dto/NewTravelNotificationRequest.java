package com.ot6.proposta.travel.dto;

import java.time.LocalDate;

public class NewTravelNotificationRequest {

    private String destino;
    private LocalDate validoAte;

    public NewTravelNotificationRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
