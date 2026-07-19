package com.portfolio.my_portfolio_backen.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Education {

    private Long id;

    @NotBlank (message = "el nombre de la carrera no puede estar vacio")
    private String degree;

    @NotBlank (message = "el nombre de la institucion no puede estar vacio")
    private String institution;

    @NotNull (message = "la fecha de inicio no puede estar vacia")
    @PastOrPresent(message = "la fecha debe ser en el pasado")
    private LocalDate starDate;

    @PastOrPresent(message = "la fecha de fin no puede ser futura")
    private LocalDate endDate; //puede ser nula

    @NotBlank (message = "la descripcion no puede estar vacia")
    private String description;

    private Long personalInfoId; //clave foranea
}
