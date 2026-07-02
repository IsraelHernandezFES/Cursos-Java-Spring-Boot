package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.model.Education;
import com.portfolio.my_portfolio_backen.repository.IEducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements IEducatonService{

    private final IEducationRepository educationRepository;


    @Override
    public Education save(Education education) {
        // asegurar que la fecha de inicio no sea nula
        if (education.getStarDate() == null){
            throw new IllegalArgumentException("no puede estar vacia la fecha de inicio");

        }

        //la fecha de incio no puede ser despues de la del fin
        if (education.getEndDate() != null && education.getStarDate().isAfter(education.getEndDate())){
            throw new IllegalArgumentException("la fecha de inicio no puede ser despues que la fecha de fin");

        }

        return educationRepository.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
         educationRepository.deleteById(id);
    }

    @Override
    public List<Education> findByPersonalInfoId(Long personalInfdId) {
        return educationRepository.findByPersonalInfoId(personalInfdId);
    }
}
