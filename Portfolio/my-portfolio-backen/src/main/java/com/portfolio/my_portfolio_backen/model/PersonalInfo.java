package com.portfolio.my_portfolio_backen.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    private Long id;  // llave primaria

    @NotBlank (message = "El nombre no puede estar vacio") //validacion para que el nombre no sea vacio
    private String firstName;
    @NotBlank (message = "El apellido no puede estar vacio")
    private String lastName;
    @NotBlank (message = "El titulo no puede estar vacio")
    private String title;
    @NotBlank (message = "Necesitas una descripcion del perfil")
    private String profileDescription;
    @NotBlank (message = "La imagen no puede estar vacia")
    private String profileImageURL;
    @Min(value = 0, message = "los anios de experiencia no pueden ser negativos")//el anio de experiencia minimo es 0 no valores negativos
    private Integer yearsOfExperience;
    @Email (message = "Email no valido")
    private String email;
    @NotBlank (message = "el telefono no puede estar vaio")
    private String phone;
    @NotBlank (message = "Necesitas un link de Linkedin")
    private String linkedinUrl;
    @NotBlank (message = "Necesitas un link de github")
    private String githubUrl;

}
