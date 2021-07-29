package com.ot6.proposta.proposal.dto;

import com.ot6.proposta.proposal.Proposal;
import com.ot6.proposta.shared.validation.constraint.CpfCnpj;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NewProposalRequest {

    @NotBlank
    @CpfCnpj
    private String cpfOrCnpj;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    @Positive
    private BigDecimal salary;

    public NewProposalRequest(
            @NotBlank @CpfCnpj String cpfOrCnpj,
            @NotBlank @Email String email,
            @NotBlank String name,
            @NotBlank String address,
            @NotNull @Positive BigDecimal salary
    ) {
        this.cpfOrCnpj = cpfOrCnpj;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal toEntity(String password, String key) {


        return new Proposal(
                this.cpfOrCnpj,
                this.email,
                this.name,
                this.address,
                this.salary,
                password,
                key
        );
    }
}
