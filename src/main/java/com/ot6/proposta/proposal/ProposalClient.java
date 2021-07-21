package com.ot6.proposta.proposal;

import com.ot6.proposta.proposal.dto.ProposalAnalysisReturn;
import com.ot6.proposta.proposal.dto.ProposalDataForAnalysis;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "proposals", url = "${api-proposal-analysis}")
public interface ProposalClient {

    @RequestMapping(
            path = "/api/solicitacao",
            method = RequestMethod.GET,
            consumes = "application/json"
    )
    ProposalAnalysisReturn analyze(ProposalDataForAnalysis proposal);
}
