package com.example.LatSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LatSpring.model.dto.request.BookDto;
import com.example.LatSpring.model.dto.response.ResponseData;
import com.example.LatSpring.model.entity.Category;
import com.example.LatSpring.repository.CategoryRepository;


@Service
@Transactional
public class CategorySerivesImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
  
    private Category category;
    private ResponseData<Object> responseData;
    private List<Category> categories;

    @Override
    public ResponseData<Object> addCategory(BookDto request) {
        // TODO Auto-generated method stub
        //post
        Optional<Category> categoryOpt = categoryRepository.findCategoryByName(request.getCategoryName());
        if (categoryOpt.isPresent()) {
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Data Sudah Ada", category.getName());
        } else {
            category = new Category();
            category.setName(request.getCategoryName());
            categoryRepository.save(category);
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Add category success", category.getName());
        
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> getCategory(Boolean status) {
        // TODO Auto-generated method stub
        // validasi statusnya, user mengirimkan data status?
        if (status == null) {
            categories = categoryRepository.findAll();
        } else {
            categories = categoryRepository.findByIsDeleted(status);
        }
  
        // bisa dikasih validasi kalau list category masih kosong, belum ada data
        // category
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", categories);
        return responseData;
    }

    @Override
    public ResponseData<Object> getCategoryById(long id) {
        // TODO Auto-generated method stub
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            category = categoryOpt.get();
            category.getId();
            category.getName();
            responseData = new ResponseData<Object>(200, "success", category);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Data Id Tidak Ada", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> updateCategory(long id, BookDto request) {
        // TODO Auto-generated method stub
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            category = categoryOpt.get();
            category.setName(request.getCategoryName());
            categoryRepository.save(category);
            responseData = new ResponseData<Object>(200, "success", category);
        }else{
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data, tidak ada", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> deleteCategory(long id) {
        // TODO Auto-generated method stub
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            category = categoryOpt.get();
            category.setDeleted(true);
            categoryRepository.save(category);
            responseData = new ResponseData<Object>(200, "success", category);
        }else{
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data, tidak ada", null);
        }
        return responseData;
    }
    
}
