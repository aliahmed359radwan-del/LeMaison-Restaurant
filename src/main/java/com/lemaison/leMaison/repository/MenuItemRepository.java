package com.lemaison.leMaison.repository;

import com.lemaison.leMaison.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {}
