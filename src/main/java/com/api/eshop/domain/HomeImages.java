package com.api.eshop.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeImages  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String indexImageUrl;
    private String name;

    private  boolean  bestMobile;
    private  boolean  bestLaptop;
    private  boolean  bestHandFree;
    private  boolean selectedBrands;

}
