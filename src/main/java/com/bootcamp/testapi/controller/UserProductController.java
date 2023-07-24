package com.bootcamp.testapi.controller;

import com.bootcamp.testapi.dto.UserProductDto;
import com.bootcamp.testapi.entity.UserProduct;
import com.bootcamp.testapi.service.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/userproduct")
@RequiredArgsConstructor
public class UserProductController {
    private final UserProductService service;

    @GetMapping
    public ResponseEntity<List<UserProduct>> findAll() {
        List<UserProduct> product = this.service.findAll();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProduct> findById(
            @PathVariable(name = "id") Integer id
    ) {
        UserProduct userProduct = this.service.findById(id);
        return ResponseEntity.ok(userProduct);
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody UserProductDto.save data
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
            @RequestBody UserProductDto.save data
    ) {
        this.service.update(id,data);
        return ResponseEntity.ok("data berhasil diubah");
    }
}
