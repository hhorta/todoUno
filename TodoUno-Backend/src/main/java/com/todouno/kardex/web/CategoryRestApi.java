package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.CategoryDTO;
import com.todouno.kardex.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin({"*"})
public class CategoryRestApi {

    private final CategoryService service;

    @Autowired
    public CategoryRestApi(CategoryService categoryService){this.service=categoryService;}

    @GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> getAllCategory() throws ServiceException {
        return service.getAllCategory();
    }

    @GetMapping(value = "/category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Integer id)
            throws ServiceException {
        CategoryDTO entity = service.getCategoryById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO categoryDTO) throws ServiceException {
        CategoryDTO created = service.createCategory(categoryDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/category", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> updateCategory (@RequestBody CategoryDTO categoryDTO) throws ServiceException{
        CategoryDTO update = service.updateCategory(categoryDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }
}
