package com.ot6.proposta.travel;

import com.ot6.proposta.card.Card;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "travels")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destination;

    @NotNull
    @Future
    private LocalDate endsIn;

    @NotBlank
    private String ipRequestAddress;

    @NotBlank
    private String userAgent;

    @ManyToOne
    private Card card;

    private LocalDateTime creationInstant = LocalDateTime.now();

    public Travel(
            String destination,
            LocalDate endsIn,
            String ipRequestAddress,
            String userAgent,
            Card card
    ) {
        this.destination = destination;
        this.endsIn = endsIn;
        this.ipRequestAddress = ipRequestAddress;
        this.userAgent = userAgent;
        this.card = card;
    }
}
