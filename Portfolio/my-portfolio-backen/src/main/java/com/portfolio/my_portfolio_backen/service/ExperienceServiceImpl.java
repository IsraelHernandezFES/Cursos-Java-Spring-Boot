package com.portfolio.my_portfolio_backen.service;

import com.portfolio.my_portfolio_backen.model.Experience;
import com.portfolio.my_portfolio_backen.repository.IExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements IExperienceService{

    private final IExperienceRepository experienceRepository;
    @Override
    public Experience save(Experience experience) {
        // asegurar que la fecha de inicio no sea nula
        if (experience.getStartDate() == null){
            throw new IllegalArgumentException("no puede estar vacia la fecha de inicio");

        }

        //la fecha de incio no puede ser despues de la del fin
        if (experience.getEndDate() != null && experience.getStartDate().isAfter(experience.getEndDate())){
            throw new IllegalArgumentException("la fecha de inicio no puede ser despues que la fecha de fin");

        }

        //validar que los nombres de titulo y de la compania no esten vacios
        if (experience.getJobTitle() == null || experience.getJobTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("el titulo del trabajo no puede estar vacio");

        }
        if(experience.getCompanyName()== null || experience.getCompanyName().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre de la compania no puede estar vacio");

        }

        return experienceRepository.save(experience);
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }

    @Override
    public List<Experience> findByPersonalInfoId(Long personalInfdId) {
        return experienceRepository.findByPersonalInfoId(personalInfdId);
    }
}
