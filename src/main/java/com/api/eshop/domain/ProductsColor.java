package com.api.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class ProductsColor {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String color;
    private String ShowColor;



    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "ProductsColor_fk_1"))
    @JsonBackReference("colorID")
    private Products  colorID ;
}
