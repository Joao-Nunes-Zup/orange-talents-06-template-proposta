package com.ot6.proposta.proposal.dto;

import com.ot6.proposta.shared.validation.constraint.CpfCnpj;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ProposalDataForAnalysis {

    @NotBlank
    @CpfCnpj
    private String documento;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private Long idProposta;

    public ProposalDataForAnalysis(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
