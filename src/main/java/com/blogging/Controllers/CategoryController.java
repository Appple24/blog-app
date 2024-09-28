package com.blogging.Controllers;


import com.blogging.Service.CategoryService;
import com.blogging.payloads.CategoryDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService service;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createcat(@Valid @RequestBody CategoryDto dto)
    {
        return ResponseEntity.ok(service.createCategory(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updatecat(@Valid @RequestBody CategoryDto dto,@Valid @PathVariable Integer id)
    {
        return ResponseEntity.ok(service.updatecategory(dto,id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getcat(@Valid @PathVariable Integer id)
    {
        return ResponseEntity.ok(service.getById(id));
    }
    @DeleteMapping ("/{id}")
    public void deletecat(@Valid @PathVariable Integer id)
    {
        service.deleteCategory(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> updatecat()
    {
        return ResponseEntity.ok(service.getAllCat());
    }




}
