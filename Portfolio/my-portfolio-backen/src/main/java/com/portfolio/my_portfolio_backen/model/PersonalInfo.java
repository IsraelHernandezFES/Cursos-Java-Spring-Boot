package com.portfolio.my_portfolio_backen.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {

    private Long id;  // llave primaria
    private String firstName;
    private String lastName;
    private String title;
    private String profileDescription;
    private String profileImageURL;
    private Integer yearsOfExperience;
    private String email;
    private String phone;
    private String linkedinUrl;
    private String githubUrl;

}
