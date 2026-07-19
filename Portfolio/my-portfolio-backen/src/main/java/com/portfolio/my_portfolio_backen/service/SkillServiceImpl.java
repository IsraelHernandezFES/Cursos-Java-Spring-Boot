package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Skill;
import com.portfolio.my_portfolio_backen.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService{

    private final ISkillRepository skillRepository;

    private final Validator validator;
    @Override
    @Transactional
    public Skill save(Skill skill) {
        BindingResult result= new BeanPropertyBindingResult(skill,"skill"); //asocia los erroes a este objeto
        validator.validate(skill, result); //valida si hay errores
        if (result.hasErrors()){
            throw new ValidationException(result); //muestra la excepcion personalizada

        }

        return skillRepository.save(skill); //ejucuta en dado caso de que no haya errores

    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos
    public List<Skill> findByPersonalInfoId(Long personalInfdId) {
        return skillRepository.findByPersonalInfoId(personalInfdId);
    }
}
