package com.portfolio.my_portfolio_backen.repository;

import com.portfolio.my_portfolio_backen.model.PersonalInfo;
import com.sun.jdi.LongValue;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository //esta clase es un repositorio y su trabajo es interactuar con la base de datos
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository{

    //para interactuar con la base de datos vamos utilizar jdbc template
    private final JdbcTemplate jdbcTemplate;

    //row Mapper trae los elementos del sql y los convierte en parametros java , los parsea para poderlos trabajar
     private final RowMapper<PersonalInfo> personalInfoRowMapper = (rs, rowNum) -> {
         PersonalInfo info = new PersonalInfo();
         info.setId(rs.getLong("id"));
         info.setFirstName(rs.getString("first_name"));
         info.setLastName(rs.getString("last_name"));
         info.setTitle(rs.getString("title"));
         info.setProfileDescription(rs.getString("profile_description"));
         info.setProfileImageURL(rs.getString("profile_image_url"));
         info.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class)); //usar getObject para Nulos
         info.setEmail(rs.getString("email"));
         info.setPhone(rs.getString("phone"));
         info.setLinkedinUrl(rs.getString("linkedin_url"));
         info.setGithubUrl(rs.getString("github_url"));
         return info;
    };
    @Override
    public PersonalInfo save(PersonalInfo personalInfo) { //para guardar y actualizar
        if(personalInfo.getId()==null){
            String sql = "INSERT INTO personal_info (first_name, last_name, title , profile_description, " +
                    "profile_image_url, years_of_experience, email, phone, linkedin_url, github_url)" +
                    "VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder(); //nos ayuda a recuperar el nuevo insert
            jdbcTemplate.update(connection -> {
                PreparedStatement ps= connection.prepareStatement(sql, new String[]{"id"}); //le decimos que queremos recuperar el id
                ps.setString(1,personalInfo.getFirstName());
                ps.setString(2,personalInfo.getLastName());
                ps.setString(3,personalInfo.getTitle());
                ps.setString(4,personalInfo.getProfileDescription());
                ps.setString(5,personalInfo.getProfileImageURL());
                //para tipos primitivos donde pueden ser nulos se usa SetNull si el valor es null
                if(personalInfo.getYearsOfExperience() != null){
                    ps.setInt(6,personalInfo.getYearsOfExperience());
                }else{
                    ps.setNull(6, Types.INTEGER );
                }
                ps.setString(7,personalInfo.getEmail());
                ps.setString(8,personalInfo.getPhone());
                ps.setString(9,personalInfo.getLinkedinUrl());
                ps.setString(10,personalInfo.getGithubUrl());

                return ps;

            } , keyHolder);

            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue()); //si retorna un id nulo la aplicacion se detiene
        }else{
            String sql = "UPDATE personal_info SET first_name=? , last_name=? , title = ? , profile_description=? ," +
                    "profile_image_url=? , years_of_experience=?, email=? , phone= ? , linkedin_url=? , github_url=? " +
                    "WHERE id = ?" ;
            jdbcTemplate.update(sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageURL(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhone(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId());

        }

        return personalInfo;
    }

    //esta es aceptable pero nos puede devolver multiples id
//    @Override
//    public Optional<PersonalInfo> findById(Long id) {
//        String sql = "Select * From personal_info Where id = ?";
//        List<PersonalInfo> infos = jdbcTemplate.query(sql, personalInfoRowMapper);
//        return infos.stream().findFirst();
//    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        String sql = "Select * From personal_info Where id = ?";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInfoRowMapper,id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "Select * From personal_info"; //hacemos la consulta

        return jdbcTemplate.query(sql, personalInfoRowMapper ); //llamos a jdbc y nuestro rowMapper
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From personal_info Where id = ?";
        jdbcTemplate.update(sql,id);

    }
}
