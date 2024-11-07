package com.api.eshop.controller;



import com.api.eshop.repository.SixIconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("SevenIcon")
public class SixIconController {
    @Autowired
    SixIconRepository repository;

    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity getAllIcon(){ return  new ResponseEntity(repository.findAll(), HttpStatus.OK);};

}
