package com.api.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductIAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String operatingsystem;
    private String screensize;
    private String resolution;
    private String chip;
    private String processor;
    private String internalmemory;
    private String ram;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "ProductIAttribute_fk_1"))
    @JsonBackReference("product")
    private Products product;
}
