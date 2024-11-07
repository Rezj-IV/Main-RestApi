package com.api.eshop.controller;


import com.api.eshop.repository.HomeImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("HomeImages ")
public class HomeImagesController {
    @Autowired
    HomeImagesRepository repository;

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity getAll(){ return  new ResponseEntity(repository.findAll(), HttpStatus.OK);};

    @GetMapping("{id}")
    @CrossOrigin("*")
    public ResponseEntity getById(@PathVariable Integer id)
    {
        return  new ResponseEntity(repository.findById(id), HttpStatus.OK);


    };

}
