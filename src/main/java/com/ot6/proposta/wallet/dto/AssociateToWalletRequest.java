package com.ot6.proposta.wallet.dto;

public class AssociateToWalletRequest {

    private String email;
    private String carteira;

    public AssociateToWalletRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
