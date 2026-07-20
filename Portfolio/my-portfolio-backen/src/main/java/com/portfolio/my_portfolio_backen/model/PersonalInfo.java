package com.portfolio.my_portfolio_backen.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    private Long id;  // llave primaria

    @NotBlank (message = "El nombre no puede estar vacio") //validacion para que el nombre no sea vacio
    @Size (min = 2 , max = 50 , message = "El nomnbre debe de tener entre 2 y 50 caracteres")
    private String firstName;

    @NotBlank (message = "El apellido no puede estar vacio")
    @Size (min = 2 , max = 50 , message = "El apellido debe de tener entre 2 y 50 caracteres")
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
    @URL(message = "Necesitas un link de Linkedin")
    private String linkedinUrl;
    @URL (message = "Necesitas un link de github")
    private String githubUrl;

}
