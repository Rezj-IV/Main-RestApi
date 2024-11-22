package com.api.eshop.controller;

import com.api.eshop.repository.ShowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ShowController")
public class ShowController {
    @Autowired
    ShowRepository repository;

    @GetMapping
    @CrossOrigin("*")

    public ResponseEntity getAll(){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return  new ResponseEntity(repository.findAll(), HttpStatus.OK);};
}
