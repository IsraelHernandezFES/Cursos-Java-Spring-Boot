package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.portfolio.my_portfolio_backen.repository.IPersonalInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service //recibe la logica de nuestra aplicacion , es un servicio , procesar informacion y validar operaciones

public class PersonalInfoServiceImpl implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository; //Principio Solid , es mejor depender de una interface y no de una clase concreta , para si en un futuro necesitamos hacer un cambio sea mas facil

    public PersonalInfoServiceImpl(IPersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }


    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {

        return personalInfoRepository.save(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
