package com.api.eshop.repository;

import com.api.eshop.domain.HomeImages;
import com.api.eshop.domain.MorvaridShop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MorvaridShopRepository extends JpaRepository<MorvaridShop, Integer> {
    List<MorvaridShop> findBySeller(String seller);
}
