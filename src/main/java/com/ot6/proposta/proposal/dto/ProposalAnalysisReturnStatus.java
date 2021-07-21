package com.ot6.proposta.proposal.dto;

import com.ot6.proposta.proposal.Eligibility;

public enum ProposalAnalysisReturnStatus {
    SEM_RESTRICAO;

    public Eligibility getEligibility() {
        return Eligibility.ELEGIVEL;
    }
}
