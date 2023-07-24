package com.bootcamp.testapi.dao;

import com.bootcamp.testapi.dto.ProductDto;
import com.bootcamp.testapi.dto.UsersDto;
import com.bootcamp.testapi.entity.Product;
import com.bootcamp.testapi.entity.Users;
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
public class ProductDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(ProductDto.save inputData){
        String query = "INSERT INTO public.product\n" +
                "(id, \"name\", category_id, stock)\n" +
                "VALUES(:id, :name, :category_id, :stock);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", inputData.getId());
        map.addValue("name", inputData.getName());
        map.addValue("category_id", inputData.getCategory_id());
        map.addValue("stock", inputData.getStock());

        this.jdbcTemplate.update(query, map);

    }

    public List<Product> findAll() {
        String query = "SELECT id, \"name\", category_id, stock\n" +
                "FROM public.product;\n";

        return this.jdbcTemplate.query(query, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory_id(rs.getInt("category_id"));
                product.setStock(rs.getInt("stock"));
                return product;
            }
        });
    }

    public Optional<Product> findById(Integer id) {
        String query = "SELECT id, \"name\", category_id, stock\n" +
                "FROM public.product where id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id",id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Product>>() {
                @Override
                public Optional<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setCategory_id(rs.getInt("category_id"));
                    product.setStock(rs.getInt("stock"));
                    return Optional.of(product);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.product\n" +
                "WHERE id=:id;\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id",id);
        this.jdbcTemplate.update(query,map);
    }

    public void update (Integer id, ProductDto.save editData) {
        String query = "UPDATE public.product\n" +
                "SET \"name\"=:name, category_id=:category_id, stock=:stock\n" +
                "WHERE id=:id;\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("name", editData.getName());
        map.addValue("category_id", editData.getCategory_id());
        map.addValue("stock", editData.getStock());

        this.jdbcTemplate.update(query, map);
    }
}
