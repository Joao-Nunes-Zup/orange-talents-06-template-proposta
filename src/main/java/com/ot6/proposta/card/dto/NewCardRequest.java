package com.ot6.proposta.card.dto;

import com.ot6.proposta.card.Card;
import com.ot6.proposta.proposal.Proposal;
import com.ot6.proposta.proposal.ProposalRepository;
import com.ot6.proposta.shared.validation.constraint.CpfCnpj;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class NewCardRequest {

    @NotBlank
    @CpfCnpj
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    @Positive
    private String idProposta;

    public NewCardRequest(
            @NotBlank @CpfCnpj String documento,
            @NotBlank @CreditCardNumber String nome,
            @NotNull @Positive String idProposta,
            @NotBlank String password,
            @NotBlank String key
    ) {
        TextEncryptor textEncryptor = Encryptors.text(password, key);
        String decryptedDocument = textEncryptor.decrypt(documento);

        this.documento = decryptedDocument;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
