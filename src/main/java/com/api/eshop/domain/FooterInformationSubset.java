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
public class FooterInformationSubset{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FooterInformatinSubset_fk_1"))
    @JsonBackReference("footerInformatin")
    private FooterInformation footerInformatin;
}
