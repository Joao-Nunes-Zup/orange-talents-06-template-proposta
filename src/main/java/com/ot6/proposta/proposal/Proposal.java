package com.ot6.proposta.proposal;

import com.ot6.proposta.shared.validation.constraint.CpfCnpj;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

@Entity
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @CpfCnpj
    @Column(nullable = false, unique = true)
    private String cpfOrCnpj;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String address;

    @NotNull
    @Positive
    @Column(nullable = false)
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

    public boolean exists(ProposalRepository proposalRepository) {
        Optional<Proposal> cpfOrCnpj = proposalRepository.findByCpfOrCnpj(this.cpfOrCnpj);
        return cpfOrCnpj.isPresent();
    }

    public URI generateProposalUri(UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("/proposal/{id}").buildAndExpand(this.id).toUri();
    }
}
