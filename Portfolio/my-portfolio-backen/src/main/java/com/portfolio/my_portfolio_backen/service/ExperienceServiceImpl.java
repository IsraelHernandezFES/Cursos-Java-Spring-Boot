package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Experience;
import com.portfolio.my_portfolio_backen.repository.IExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements IExperienceService{

    private final IExperienceRepository experienceRepository;

    private final Validator validator;
    @Override
    @Transactional
    public Experience save(Experience experience) {
        BindingResult result = new BeanPropertyBindingResult(experience, "experience"); //asocia los errores a este objeto
        validator.validate(experience, result); //valida si hay errores
        if(result.hasErrors()){
            throw new ValidationException(result);//nuestra excepicion personalizada
        }

        return experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public List<Experience> findByPersonalInfoId(Long personalInfdId) {
        return experienceRepository.findByPersonalInfoId(personalInfdId);
    }
}
