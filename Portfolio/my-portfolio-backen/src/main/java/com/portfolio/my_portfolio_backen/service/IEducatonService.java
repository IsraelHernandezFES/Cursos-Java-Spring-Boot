package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducatonService {
    Education save(Education education);
    Optional<Education> findById(Long id);
    List<Education> findAll();
    void deleteById(Long id);
    List<Education> findByPersonalInfoId(Long personalInfdId);
}
