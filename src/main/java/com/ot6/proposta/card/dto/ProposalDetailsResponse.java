package com.ot6.proposta.card.dto;

import com.ot6.proposta.proposal.Eligibility;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.math.BigDecimal;

public class ProposalDetailsResponse {

    private Long id;
    private String cpfOrCnpj;
    private String email;
    private String name;
    private String address;
    private BigDecimal salary;
    private Eligibility eligibility;
    private String cardNumber;

    public ProposalDetailsResponse(
            Long id,
            String cpfOrCnpj,
            String email,
            String name,
            String address,
            BigDecimal salary,
            Eligibility eligibility,
            String cardNumber,
            String password,
            String key
    ) {
        TextEncryptor textEncryptor = Encryptors.text(password, key);
        String decryptedDocument = textEncryptor.decrypt(cpfOrCnpj);

        this.id = id;
        this.cpfOrCnpj = decryptedDocument;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.eligibility = eligibility;
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public Eligibility getEligibility() {
        return eligibility;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
