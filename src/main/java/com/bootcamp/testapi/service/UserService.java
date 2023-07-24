package com.bootcamp.testapi.service;

import com.bootcamp.testapi.dao.UserDao;
import com.bootcamp.testapi.dto.UsersDto;
import com.bootcamp.testapi.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao dao;

    public void save(UsersDto.save data){
        this.dao.save(data);
    }

    public List<Users> findAll(){
        return this.dao.findAll();
    }

    public Users findById(Integer id){
        return this.dao.findById(id).orElseThrow(() -> new RuntimeException("User dengan id " + id + " tidak ditemukan"));
    }

    public void delete(Integer id) {
        this.dao.delete(id);
    }

    public void update(Integer id, UsersDto.save data) {
        findById(id);
        this.dao.update(id,data);
    }
}
