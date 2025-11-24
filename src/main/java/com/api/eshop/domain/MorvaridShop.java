package com.api.eshop.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorvaridShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private String category;
    private String seller;
    private String latitude;
    private String longitude;

    @Column(length = 1000)
    private String indexImageUrl;

    @OneToMany(mappedBy = "morvaridShop" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JsonManagedReference("morvaridShop")
    private List<MorvaridShopImages> images;








    public MorvaridShop(String name, int price,String category, String indexImageUrl , String seller) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.indexImageUrl = indexImageUrl;
        this.seller = seller;
    }

    // Getters & Setters
    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getIndexImageUrl() { return indexImageUrl; }
    public void setIndexImageUrl(String indexImageUrl) { this.indexImageUrl = indexImageUrl; }

    public String getSeller() { return seller; }
    public void setSeller(String seller) { this.seller = seller; }
}







