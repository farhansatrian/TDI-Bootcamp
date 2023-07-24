package com.bootcamp.testapi.dto;

import lombok.Data;

public class UsersDto {

    @Data
    public static class save{
        private int id;
        private String name;
        private String email;
        private int phone;
    }
}
