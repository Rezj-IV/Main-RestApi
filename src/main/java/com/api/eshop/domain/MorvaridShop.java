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

}
