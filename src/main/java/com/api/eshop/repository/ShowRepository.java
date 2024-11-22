package com.api.eshop.repository;

import com.api.eshop.domain.ShowMenu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowMenu, Integer> {

}