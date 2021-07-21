package com.ot6.proposta.proposal.dto;

import com.ot6.proposta.proposal.Eligibility;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProposalAnalysisReturn {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private ProposalAnalysisReturnStatus resultadoSolicitacao;

    @NotNull
    private Long idProposta;

    public ProposalAnalysisReturn(
            String documento,
            String nome,
            ProposalAnalysisReturnStatus resultadoSolicitacao,
            Long idProposta
    ) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public Eligibility getEligibility() {
        return resultadoSolicitacao.getEligibility();
    }
}
