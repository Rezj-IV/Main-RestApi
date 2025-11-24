package com.api.eshop.controller;

import com.api.eshop.domain.MorvaridShop;
import com.api.eshop.domain.Products;
import com.api.eshop.repository.MorvaridShopRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("MorvaridShop")
public class MorvaridShopController {
    @Autowired
    private  MorvaridShopRepository repository;



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



    @GetMapping("seller/{seller}")
    @CrossOrigin("*")
    public ResponseEntity getBySeller(@PathVariable String  seller)
    {
        return  new ResponseEntity(repository.findBySeller(seller), HttpStatus.OK);


    }








    @Value("${upload.dir}")
    private String uploadDir;


    @Value("${server.base-url}")
    private String baseUrl;



    public MorvaridShopController(MorvaridShopRepository repository) {
        this.repository = repository;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createPost(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("seller") String seller


            ) throws IOException {

        String indexImageUrl = null;

        // اگر تصویری ارسال شده باشد، آن را ذخیره کن
        if (image != null && !image.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            Path path = Paths.get(uploadDir + File.separator + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

//            indexImageUrl = path.toString();

            indexImageUrl = baseUrl + "/uploads/" + fileName;

        }

        MorvaridShop post = new MorvaridShop(name, price, category , indexImageUrl , seller );
        repository.save(post);

        return ResponseEntity.ok(post);
    }








}
