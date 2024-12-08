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

    @GetMapping("bestMobile")
    @CrossOrigin("*")
    public ResponseEntity getAllbestMobile() {

        return new ResponseEntity(repository.findByBestMobileIsTrue(), HttpStatus.OK);
    }
    @GetMapping("bestLaptop")
    @CrossOrigin("*")
    public ResponseEntity getAllbestLaptop() {

        return new ResponseEntity(repository.findByBestLaptopIsTrue(), HttpStatus.OK);
    }
    @GetMapping("bestHandFree")
    @CrossOrigin("*")
    public ResponseEntity getAllbestHandFree() {

        return new ResponseEntity(repository.findByBestHandFreeIsTrue(), HttpStatus.OK);
    } @GetMapping("selectedBrands")
    @CrossOrigin("*")
    public ResponseEntity getAllselectedBrands() {

        return new ResponseEntity(repository.findBySelectedBrandsIsTrue(), HttpStatus.OK);
    }


}
