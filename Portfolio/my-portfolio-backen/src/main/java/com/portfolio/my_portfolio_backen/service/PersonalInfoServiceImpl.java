package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.exception.ValidationException;
import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.repository.IPersonalInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;


@Service //recibe la logica de nuestra aplicacion , es un servicio , procesar informacion y validar operaciones
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository; //Principio Solid , es mejor depender de una interface y no de una clase concreta , para si en un futuro necesitamos hacer un cambio sea mas facil

    private final Validator validator;


    @Override
    @Transactional //transaccion con base de datos
    public PersonalInfo save(PersonalInfo personalInfo) {
        BindingResult result = new BeanPropertyBindingResult(personalInfo, "personalInfo"); //asocia los errores a este objeto
        validator.validate(personalInfo, result); //valida si hay errores
        if(result.hasErrors()){
            throw new ValidationException(result);//nuestra excepicion personalizada
        }
        return personalInfoRepository.save(personalInfo); //ejecuta en dado caso de que no haya errores
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos , ahorra memoria por que no busca verificar errores en una operaion
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true) //indicamos que solo va a leer la base de datos
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    @Transactional //tambien es una operacion con base de datos
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
