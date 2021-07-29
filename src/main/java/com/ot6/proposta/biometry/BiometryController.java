package com.ot6.proposta.biometry;

import com.ot6.proposta.biometry.dto.NewBiometryRequest;
import com.ot6.proposta.biometry.validation.BiometryShouldBeInBase64Format;
import com.ot6.proposta.card.Card;
import com.ot6.proposta.card.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometrics")
public class BiometryController {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BiometryRepository biometryRepository;

    @Autowired
    private BiometryShouldBeInBase64Format biometryShouldBeInBase64Format;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(biometryShouldBeInBase64Format);
    }

    @Transactional
    @PostMapping("/{cardId}")
    public ResponseEntity<?> criar(
            @RequestBody @Valid NewBiometryRequest biometryRequest,
            @PathVariable String cardId,
            UriComponentsBuilder uriBuilder
    ) {
        Optional<Card> card = cardRepository.findById(cardId);

        if (card.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Biometry biometry = biometryRequest.toEntity(card.get());
        biometryRepository.save(biometry);

        URI uri = biometry.generateUri(uriBuilder);
        return ResponseEntity.created(uri).build();
    }
}
