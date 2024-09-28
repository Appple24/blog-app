package com.blogging.Service.Impl;

import com.blogging.Repository.CategoryRepo;
import com.blogging.Service.CategoryService;
import com.blogging.entites.Category;
import com.blogging.exceptions.ResourceNotFoundException;
import com.blogging.payloads.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo repo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto dto) {
        Category cat=modelMapper.map(dto,Category.class);
         Category createdCat=this.repo.save(cat);
        return this.modelMapper.map(createdCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updatecategory(CategoryDto dto, Integer catid) {
        Category cat=this.repo.findById(catid).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",catid));
        cat.setCatName(dto.getCatName());
        cat.setCatDescription(dto.getCatDescription());
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id) );
        repo.deleteById(id);
    }

    @Override
    public CategoryDto getById(Integer id) {
        Category cat=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",id) );
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCat() {
        List<Category> catList=repo.findAll();
        List<CategoryDto>catDtoList=new ArrayList<>();
        for(Category i : catList)
        {
            catDtoList.add(modelMapper.map(i,CategoryDto.class));
        }
        return catDtoList;
    }
}
