package com.api.eshop.controller;


import com.api.eshop.domain.Products;
import com.api.eshop.repository.ProductsRepository;
import com.api.eshop.service.ProductsService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("myProducts")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsService service;


    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity getAll() {
        List<Products> result = service.getAll();
        for (Products product : result) {
            product.getImages().size();
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin("*")
    public ResponseEntity getById(@PathVariable long id) {
        Products result = service.getById(id);
        if(result!=null) {
            result.getImages().size();
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else
            return new ResponseEntity(result , HttpStatus.BAD_REQUEST);
    }


    @GetMapping("name/{name}")
    @CrossOrigin("*")
    public ResponseEntity getByName(@PathVariable String name) {
        Products result = service.getByName(name);
        if(result!=null) {
            result.getImages().size();
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else
            return new ResponseEntity(result , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("type/{type}")
    @CrossOrigin("*")
    public ResponseEntity getByType(@PathVariable String  type)
    {
        return  new ResponseEntity(productsRepository.findByTypeContains(type), HttpStatus.OK);
    }


    @GetMapping("category/{category}")
    @CrossOrigin("*")
    public ResponseEntity getByCategory(@PathVariable String  category)
    {
        return  new ResponseEntity(productsRepository.findByCategoryContains(category), HttpStatus.OK);
    }



    @GetMapping("incredibleOffers")
    @CrossOrigin("*")
    public ResponseEntity getAllIncredibleOffers() {
        return new ResponseEntity(service.getAllIncredibleOffers(), HttpStatus.OK);
    }



    @GetMapping("dailySuggests")
    @CrossOrigin("*")
    public ResponseEntity getAllDailySuggests() {
        List<Products> result = service.getAllDailySuggests();
        return new ResponseEntity(result, HttpStatus.OK);
    }


    @GetMapping("gaming")
    @CrossOrigin("*")
    public ResponseEntity getAllGaming() {
        return new ResponseEntity(service.getAllGaming(), HttpStatus.OK);
    }


    @GetMapping("rjPlus")
    @CrossOrigin("*")
    public ResponseEntity getAllrjPlus() {

        return new ResponseEntity(productsRepository.findByRjPlusIsTrue(), HttpStatus.OK);
    }


    @GetMapping("bestSelling")
    @CrossOrigin("*")
    public ResponseEntity getAllBestSelling() {
        List<Products> result = service.getAllBestSelling();
        return new ResponseEntity(result, HttpStatus.OK);
    }



    @GetMapping("flagBearer")
    @CrossOrigin("*")
    public ResponseEntity getAllFlagBearer() {
        return new ResponseEntity(service.getAllFlagBearer(), HttpStatus.OK);
    }



    @GetMapping("installmentGoods")
    @CrossOrigin("*")
    public ResponseEntity getAllInstallmentGoods() {
        List<Products> result = service.getAllInstallmentGoods();
        return new ResponseEntity(result, HttpStatus.OK);
    }



    @GetMapping("search/{textToSearch}")
    @CrossOrigin("*")
    public ResponseEntity getAllDailySuggests(@PathVariable String textToSearch) {
        List<Products> result = service.searchProducts(textToSearch);
        return new ResponseEntity(result, HttpStatus.OK);
    }


}
