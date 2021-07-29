package com.ot6.proposta.biometry;

import com.ot6.proposta.card.Card;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.time.LocalDateTime;

@Entity
@Table(name = "biometrics")
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fingerprint;

    @ManyToOne
    private Card card;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Biometry() {}

    public Biometry(String fingerprint, Card card) {
        this.fingerprint = fingerprint;
        this.card = card;
    }

    public URI generateUri(UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("/biometrics/{id}").buildAndExpand(this.id).toUri();
    }
}
