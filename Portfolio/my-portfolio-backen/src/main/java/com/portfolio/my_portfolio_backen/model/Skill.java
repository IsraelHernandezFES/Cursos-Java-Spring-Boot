package com.portfolio.my_portfolio_backen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    private Long id;
    private String name;
    private Integer levelPorcentage;
    private String iconClass;
    private Long personalInfoId;

}
