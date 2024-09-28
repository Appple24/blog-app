package com.blogging.Service;
import java.util.*;
import com.blogging.payloads.CategoryDto;

public interface CategoryService {
//    createing a cateogry
     CategoryDto createCategory(CategoryDto dto);
//     updatinf a cat
    CategoryDto updatecategory(CategoryDto dto,Integer id);
//    deleting a cat
    void deleteCategory(Integer id);
//    fetching a cat
    CategoryDto getById(Integer id);
//    get all cat
    List<CategoryDto> getAllCat();


}
