package com.bootcamp.testapi.dao;

import com.bootcamp.testapi.dto.CategoryDto;
import com.bootcamp.testapi.dto.ProductDto;
import com.bootcamp.testapi.entity.Category;
import com.bootcamp.testapi.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(CategoryDto.save inputData){
        String query = "INSERT INTO public.category\n" +
                "(id, \"name\")\n" +
                "VALUES(:id, :name);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", inputData.getId());
        map.addValue("name", inputData.getName());

        this.jdbcTemplate.update(query, map);

    }

    public List<Category> findAll() {
        String query = "SELECT id, \"name\"\n" +
                "FROM public.category;\n";

        return this.jdbcTemplate.query(query, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                return category;
            }
        });
    }

    public Optional<Category> findById(Integer id) {
        String query = "SELECT id, \"name\"\n" +
                "FROM public.category where id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Category>>() {
                @Override
                public Optional<Category> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Category category = new Category();
                    category.setId(rs.getInt("id"));
                    category.setName(rs.getString("name"));
                    return Optional.of(category);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.category\n" +
                "WHERE id=:id;\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }

    public void update (Integer id, CategoryDto.save editData) {
        String query = "UPDATE public.category\n" +
                "SET \"name\"=:name\n" +
                "WHERE id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("name", editData.getName());

        this.jdbcTemplate.update(query, map);
    }



}
