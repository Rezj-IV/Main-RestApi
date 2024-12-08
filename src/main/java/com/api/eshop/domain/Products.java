package com.api.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.org.apache.xpath.internal.objects.XString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;

    private String nameB;

    private String category;




    private String type;



    private String seller;
    private int stock;


    private int price;
    private int priceWithDiscount = 0;

    private boolean incredibleOffers;
    private boolean dailySuggest;
    private boolean gaming;
    private boolean rjPlus;
    private boolean bestSelling;
    private boolean installmentGoods;


    @Column(length = 1000)
    private String indexImageUrl;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference("product")
    private List<ProductImages> images;

     @OneToMany(mappedBy = "colorID" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
     @JsonManagedReference("colorID")
     private Set<ProductsColor> colorID;



  @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonManagedReference("product")
    private Set<ProductIAttribute> attribute;


}
