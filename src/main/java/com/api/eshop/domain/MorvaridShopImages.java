package com.api.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MorvaridShopImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "longtext")
    private String original;



    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "MorvaridShopImages_fk_1"))
    @JsonBackReference("morvaridShop")
    private MorvaridShop morvaridShop;




}
