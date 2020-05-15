package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.ProductDTO;
import com.todouno.kardex.domain.entity.Items;
import com.todouno.kardex.services.products.ProductService;
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
public class ProductRestApi {

    private final ProductService service;

    @Autowired
    public ProductRestApi(ProductService productService){this.service=productService;}

    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDTO> getAllProduct() throws ServiceException {
        return service.getAllProduct();
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Integer id)
            throws ServiceException {
        ProductDTO entity = service.getProductById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/product/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProductByCode(@PathVariable("code") String code)
            throws ServiceException {
        ProductDTO entity = service.getProductByCode(code);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) throws ServiceException {
        ProductDTO created = service.createProduct(productDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateProduct (@RequestBody ProductDTO productDTO) throws ServiceException{
        ProductDTO update = service.updateProduct(productDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }

   @GetMapping("/products/{code}/quantity/{quantity}")
    public Items details(@PathVariable String code, @PathVariable Integer quantity) throws ServiceException {
        return service.getTotalByCodeAndQuantity(code, quantity);
    }

}
