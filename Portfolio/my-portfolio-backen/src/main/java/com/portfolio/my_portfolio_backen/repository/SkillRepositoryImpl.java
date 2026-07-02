package com.portfolio.my_portfolio_backen.repository;

import com.portfolio.my_portfolio_backen.model.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository //su trabajo es interactuar con la base datos
@RequiredArgsConstructor
public class SkillRepositoryImpl implements ISkillRepository{
    //interactuar directamente con DB usamos jdbcTemplate
    private final JdbcTemplate jdbcTemplate;

    //parsea los elementos sql a objetos java
    private final RowMapper<Skill> skillRowMapper = ((rs, rowNum) -> {
        Skill skill = new Skill();
        skill.setId(rs.getLong("id"));
        skill.setName(rs.getString("name"));
        skill.setLevelPorcentage(rs.getInt("level_percentage"));
        skill.setIconClass(rs.getString("icon_class"));
        skill.setPersonalInfoId(rs.getLong("personal_info_id"));
        return skill;

    });

    @Override
    public Skill save(Skill skill) {

        if(skill.getId()==null){
            String sql = "Insert Into skills (name , level_percentage , icon_class , personal_info_id)"  +
                    "VALUES ( ? , ? , ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();//recuperamos el id recien creado
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
                ps.setString(1, skill.getName());
                ps.setInt(2, skill.getLevelPorcentage());
                ps.setString(3, skill.getIconClass());
                ps.setLong(4,skill.getPersonalInfoId());

                return ps;
            } , keyHolder);
            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue()); //si retorna un id nulo la aplicacion se detiene

        }else{
            String sql = "UPDATE skills SET name=? , level_percentage=? , icon_class=? , personal_info_id=? WHERE id = ?";

            jdbcTemplate.update(sql,
                    skill.getName(),
                    skill.getLevelPorcentage(),
                    skill.getIconClass(),
                    skill.getPersonalInfoId(),
                    skill.getId());

        }

        return skill;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String sql = "Select * From skills Where id = ?";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, skillRowMapper,id));
        } catch (EmptyResultDataAccessException e ){
             return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "Select * From skills";

        return jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete From skills Where id = ?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public List<Skill> findByPersonalInfoId(Long personalInfdId) {
         String sql = "Select * From skills Where personal_info_id = ?";

        return jdbcTemplate.query(sql, skillRowMapper , personalInfdId);
    }
}
