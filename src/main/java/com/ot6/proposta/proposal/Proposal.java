package com.ot6.proposta.proposal;

import com.ot6.proposta.shared.validation.CpfCnpj;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Proposal(
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

    public Long getId() {
        return this.id;
    }
}
