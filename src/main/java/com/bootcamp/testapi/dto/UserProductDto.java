package com.bootcamp.testapi.dto;

import lombok.Data;

public class UserProductDto {

    @Data
    public static class save {
        private int id;
        private int user_id;
        private int product_id;
        private int quantity;
    }

}
