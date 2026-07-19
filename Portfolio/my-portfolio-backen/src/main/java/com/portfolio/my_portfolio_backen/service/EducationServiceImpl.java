package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducatonService{

    private final IEducationRepository educationRepository;

    private final Validator validator;


    @Override
    @Transactional
    public Education save(Education education) {
        BindingResult result = new BeanPropertyBindingResult(education, "education"); //asocia los errores a este objeto
        validator.validate(education, result); //valida si hay errores
        if(result.hasErrors()){
            throw new ValidationException(result);//nuestra excepicion personalizada
        }

        return educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
         educationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public List<Education> findByPersonalInfoId(Long personalInfdId) {
        return educationRepository.findByPersonalInfoId(personalInfdId);
    }
}
