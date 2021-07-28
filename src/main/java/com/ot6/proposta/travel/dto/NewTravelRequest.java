package com.ot6.proposta.travel.dto;

import com.ot6.proposta.card.Card;
import com.ot6.proposta.travel.Travel;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NewTravelRequest {

    @NotBlank
    private String destination;

    @NotNull
    @Future
    private LocalDate endsIn;

    public NewTravelRequest(String destination, LocalDate endsIn) {
        this.destination = destination;
        this.endsIn = endsIn;
    }

    public Travel toEntity(String requestIpAddress, String userAgent, Card card) {
        return new Travel(this.destination, this.endsIn, requestIpAddress, userAgent, card);
    }
}
