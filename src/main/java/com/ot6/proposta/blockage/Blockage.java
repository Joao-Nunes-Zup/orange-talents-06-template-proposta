package com.ot6.proposta.blockage;

import com.ot6.proposta.card.Card;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.time.LocalDateTime;

@Entity
@Table(name = "blockages")
public class Blockage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String clientIpAddress;

    @NotBlank
    private String userAgent;

    @ManyToOne
    private Card card;

    private LocalDateTime blockedIn = LocalDateTime.now();

    public Blockage(
            String clientIpAddress,
            String userAgent,
            Card card
    ) {
        this.clientIpAddress = clientIpAddress;
        this.userAgent = userAgent;
        this.card = card;
    }

    public URI generateUri(UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("/blockages/{id}").buildAndExpand(this.id).toUri();
    }
}
