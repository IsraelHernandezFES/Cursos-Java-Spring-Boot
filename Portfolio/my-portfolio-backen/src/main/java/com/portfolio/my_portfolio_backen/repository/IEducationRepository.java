package com.portfolio.my_portfolio_backen.repository;

import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.model.Skill;

import java.util.List;
import java.util.Optional;

public interface IEducationRepository {

    Education save(Education education);
    Optional<Education> findById(Long id);
    List<Education> findAll();
    void deleteById(Long id);
    List<Education> findByPersonalInfoId(Long personalInfdId);
}
