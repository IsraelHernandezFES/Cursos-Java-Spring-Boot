package com.portfolio.my_portfolio_backen.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Experience {

    private Long id;

    @NotBlank (message = "El titulo del trabajo no puede estar vacio")
    private String jobTitle;

    @NotBlank (message = "El nombre de la compañia no puede estar vacio")
    private String companyName;

    @NotNull (message = "la fecha de inicio no puede estar vacia")
    @PastOrPresent(message = "la fecha debe ser en el pasado")
    private LocalDate startDate;

    @PastOrPresent(message = "la fecha de fin no puede ser futura")
    private LocalDate endDate;

    @NotBlank (message = "La descripion no puede estar vacia")
    private String description;

    private Long personalInfoId;  //llave foranea

}
