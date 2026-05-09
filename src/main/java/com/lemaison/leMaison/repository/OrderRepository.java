package com.lemaison.leMaison.repository;

import com.lemaison.leMaison.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
