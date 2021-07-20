package com.ot6.proposta.proposal.dto;

import com.ot6.proposta.proposal.Proposal;
import com.ot6.proposta.shared.validation.constraint.CpfCnpj;

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

    @Override
    public String toString() {
        return "NewProposalRequest{" +
                "cpfOrCnpj='" + cpfOrCnpj + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Proposal toEntity() {
        return new Proposal(
                this.cpfOrCnpj,
                this.email,
                this.name,
                this.address,
                this.salary
        );
    }
}
