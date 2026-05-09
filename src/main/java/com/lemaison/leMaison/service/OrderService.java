package com.lemaison.leMaison.service;

import com.lemaison.leMaison.model.MenuItem;
import com.lemaison.leMaison.model.Order;
import com.lemaison.leMaison.model.OrderItem;
import com.lemaison.leMaison.repository.MenuItemRepository;
import com.lemaison.leMaison.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final MenuItemRepository menuRepo;

    public OrderService(OrderRepository orderRepo, MenuItemRepository menuRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo  = menuRepo;
    }

    public Order placeOrder(Order order, Map<Long, Integer> quantities) {
        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (Map.Entry<Long, Integer> entry : quantities.entrySet()) {
            int qty = entry.getValue();
            if (qty <= 0) continue;

            MenuItem menuItem = menuRepo.findById(entry.getKey())
                .orElseThrow(() -> new IllegalArgumentException("Unknown menu item: " + entry.getKey()));

            OrderItem item = new OrderItem(order, menuItem.getName(), qty, menuItem.getPrice());
            items.add(item);
            total += item.getSubtotal();
        }

        if (items.isEmpty()) {
            throw new IllegalStateException("Please select at least one item.");
        }

        order.setItems(items);
        order.setTotalPrice(total);
        order.setOrderDateTime(LocalDateTime.now());

        return orderRepo.save(order);
    }
}

