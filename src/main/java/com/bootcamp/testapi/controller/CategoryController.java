package com.bootcamp.testapi.controller;

import com.bootcamp.testapi.dto.CategoryDto;
import com.bootcamp.testapi.dto.ProductDto;
import com.bootcamp.testapi.entity.Category;
import com.bootcamp.testapi.entity.Product;
import com.bootcamp.testapi.service.CategoryService;
import com.bootcamp.testapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> category = this.service.findAll();
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody CategoryDto.save data
    ) {
        this.service.save(data);
        return ResponseEntity.ok("data berhasil ditambahkan");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(
            @PathVariable(name = "id") Integer id
    ) {
        Category category = this.service.findById(id);
        return ResponseEntity.ok(category);
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
            @RequestBody CategoryDto.save data
    ) {
        this.service.update(id,data);
        return ResponseEntity.ok("data berhasil diubah");
    }
}
