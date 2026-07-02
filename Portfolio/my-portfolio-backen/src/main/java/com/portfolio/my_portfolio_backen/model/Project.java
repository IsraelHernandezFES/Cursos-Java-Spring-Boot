package com.portfolio.my_portfolio_backen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String projectUrl;
    private Long personalInfoId; //llave foranea

}
