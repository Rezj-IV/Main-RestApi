package com.api.eshop.repository;



import com.api.eshop.domain.HomeImages;

import com.api.eshop.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HomeImagesRepository extends JpaRepository<HomeImages, Integer> {
    List<HomeImages> findByBestMobileIsTrue();
    List<HomeImages> findByBestLaptopIsTrue();
    List<HomeImages> findByBestHandFreeIsTrue();
    List<HomeImages> findBySelectedBrandsIsTrue();

}

