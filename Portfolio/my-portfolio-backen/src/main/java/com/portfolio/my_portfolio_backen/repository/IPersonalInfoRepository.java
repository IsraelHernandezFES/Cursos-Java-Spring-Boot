package com.portfolio.my_portfolio_backen.repository;

import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import org.springframework.beans.factory.ListableBeanFactory;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoRepository {
    PersonalInfo save(PersonalInfo personalInfo);
    Optional<PersonalInfo> findById(Long id);
    List<PersonalInfo> findAll();
    void deleteById(Long id);


}
