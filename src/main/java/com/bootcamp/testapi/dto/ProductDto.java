package com.bootcamp.testapi.dto;

import lombok.Data;

public class ProductDto {

    @Data
    public static class save{
        private int id;
        private String name;
        private int category_id;
        private int stock;
    }
}
