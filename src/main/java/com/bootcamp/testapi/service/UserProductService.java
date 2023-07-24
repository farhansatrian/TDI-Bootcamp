package com.bootcamp.testapi.service;

import com.bootcamp.testapi.dao.ProductDao;
import com.bootcamp.testapi.dao.UserProductDao;
import com.bootcamp.testapi.dto.ProductDto;
import com.bootcamp.testapi.dto.UserProductDto;
import com.bootcamp.testapi.entity.Product;
import com.bootcamp.testapi.entity.UserProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao dao;

    public void save(UserProductDto.save data){
        this.dao.save(data);
    }

    public List<UserProduct> findAll(){
        return this.dao.findAll();
    }

    public UserProduct findById(Integer id){
        return this.dao.findById(id).orElseThrow(() -> new RuntimeException("User Product dengan id " + id + " tidak ditemukan"));
    }

    public void delete(Integer id) {
        this.dao.delete(id);
    }

    public void update(Integer id, UserProductDto.save data) {
        findById(id);
        this.dao.update(id,data);
    }
}
