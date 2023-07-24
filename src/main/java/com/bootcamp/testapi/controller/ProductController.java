package com.bootcamp.testapi.controller;

import com.bootcamp.testapi.dto.ProductDto;
import com.bootcamp.testapi.dto.UsersDto;
import com.bootcamp.testapi.entity.Product;
import com.bootcamp.testapi.entity.Users;
import com.bootcamp.testapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> product = this.service.findAll();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(
            @PathVariable(name = "id") Integer id
    ) {
        Product product = this.service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody ProductDto.save data
    ) {
        this.service.save(data);
        return ResponseEntity.ok("data berhasil ditambahkan");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id
    ) {
        this.service.delete(id);
        return ResponseEntity.ok("data berhasil dihapus");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody ProductDto.save data
    ) {
        this.service.update(id,data);
        return ResponseEntity.ok("data berhasil diubah");
    }
}
