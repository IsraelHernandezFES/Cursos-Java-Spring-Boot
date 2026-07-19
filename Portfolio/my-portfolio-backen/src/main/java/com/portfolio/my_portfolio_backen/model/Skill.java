package com.portfolio.my_portfolio_backen.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {


    private Long id;

    @NotBlank (message = "el nombre de la Skill no puede estar vacio")
    private String name;

    @NotNull (message = "el porcentaje no puede ser nulo")
    @Min(value = 0 , message = "El valor del nivel de porcentaje tiene que ser mayor o igual de 0")
    @Max(value = 100 , message = "El valor del nivel de porcentaje tiene que ser menor o igual de 100")
    private Integer levelPorcentage;

    @NotBlank (message = "la clase del icono de la Skill no puede estar vacia")
    private String iconClass;

    private Long personalInfoId;

}
