package com.portfolio.my_portfolio_backen.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

public class ValidationException  extends RuntimeException{

    private final BindingResult bindingResult;


    public ValidationException(BindingResult bindingResult) {
        super ( "Errores de validacion: se encontraron " + bindingResult.getErrorCount());
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
