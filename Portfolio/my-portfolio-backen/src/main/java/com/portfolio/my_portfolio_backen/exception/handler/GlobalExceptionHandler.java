package com.portfolio.my_portfolio_backen.exception.handler;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //contiene logica de excepciones en un solo lugar para todos los controladores
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model){

        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        model.addAttribute("message", "Se encontraron Errores de Validacion");
        return "error/validation";
    }
}
