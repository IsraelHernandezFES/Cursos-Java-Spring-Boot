package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService{

    private final ISkillRepository skillRepository;
    @Override
    public Skill save(Skill skill) {
        if (skill.getLevelPorcentage()<0 || skill.getLevelPorcentage() > 100){
            throw new IllegalArgumentException("level Percentage Invalido , debe ser entre 0 y 100");

        } //logica de negocio

        return skillRepository.save(skill);

    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findByPersonalInfoId(Long personalInfdId) {
        return skillRepository.findByPersonalInfoId(personalInfdId);
    }
}
