package com.api.eshop.repository;

import com.api.eshop.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products , Long> {
    List<Products> findByIncredibleOffersIsTrue();
    List<Products> findByDailySuggestIsTrue();

    List<Products> findByGamingIsTrue();

    List<Products> findByRjPlusIsTrue();

    List<Products> findByBestSellingIsTrue();

    List<Products>  findByFlagBearerIsTrue();

    List<Products> findByInstallmentGoodsIsTrue();

    List<Products> findByNameContains(String searchText);

    List<Products> findByPriceGreaterThan(int p);

    List<Products> findByPriceBetween(int from , int to); // select * from products where price between from and to

    Products findByName(String name);

    List<Products> findByCategoryContains(String category);

    List<Products> findByTypeContains(String type);

}

