package com.bootcamp.testapi.dto;

import lombok.Data;

public class CategoryDto {

    @Data
    public static class save {
        private int id;
        private String name;
    }
}
