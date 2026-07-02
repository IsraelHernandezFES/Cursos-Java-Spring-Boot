package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillService {
    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    void deleteById(Long id);
    List<Skill> findByPersonalInfoId(Long personalInfdId);
}
