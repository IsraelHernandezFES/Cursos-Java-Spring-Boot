package com.portfolio.my_portfolio_backen.repository;

import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.model.Experience;
import com.sun.nio.file.ExtendedOpenOption;

import java.util.List;
import java.util.Optional;

public interface IExperienceRepository {

    Experience save(Experience experience);
    Optional<Experience> findById(Long id);
    List<Experience> findAll();
    void deleteById(Long id);
    List<Experience> findByPersonalInfoId(Long personalInfdId);

}
