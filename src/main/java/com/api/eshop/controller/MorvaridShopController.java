package com.api.eshop.controller;

import com.api.eshop.domain.MorvaridShop;
import com.api.eshop.domain.Products;
import com.api.eshop.repository.MorvaridShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("MorvaridShop")
public class MorvaridShopController {
    @Autowired
    private MorvaridShopRepository repository;


    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity getAll(){ return  new ResponseEntity(repository.findAll(), HttpStatus.OK);};




    @GetMapping("{id}")
    @CrossOrigin("*")
    public ResponseEntity getById(@PathVariable int id) {
        MorvaridShop result = repository.getById(id);
        if(result!=null) {
            result.getImages().size();
            return new ResponseEntity(result, HttpStatus.OK);
        }
        else
            return new ResponseEntity(result , HttpStatus.BAD_REQUEST);
    }

}
