package com.ot6.proposta.biometry.validation;

import com.ot6.proposta.biometry.dto.NewBiometryRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BiometryShouldBeInBase64Format implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NewBiometryRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        if (errors.hasErrors()) return;

        NewBiometryRequest biometryRequest = (NewBiometryRequest) obj;

        if (!biometryRequest.isFingerprintValid()) {
            errors.rejectValue(
                    "fingerprint",
                    null,
                    "A impressão digital deve estar no formato Base64"
            );
        }
    }
}
