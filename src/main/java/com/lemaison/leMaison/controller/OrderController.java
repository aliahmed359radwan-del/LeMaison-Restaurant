package com.lemaison.leMaison.controller;

import com.lemaison.leMaison.model.Order;
import com.lemaison.leMaison.repository.MenuItemRepository;
import com.lemaison.leMaison.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final MenuItemRepository menuRepo;

    public OrderController(OrderService orderService, MenuItemRepository menuRepo) {
        this.orderService = orderService;
        this.menuRepo     = menuRepo;
    }

    @GetMapping
    public String form(Model model) {
        model.addAttribute("order",  new Order());
        model.addAttribute("menuItems", menuRepo.findAll());
        return "order";
    }

    @PostMapping
    public String placeOrder(@Valid @ModelAttribute("order") Order order,
                             BindingResult result,
                             @RequestParam Map<String, String> allParams,
                             Model model) {

        model.addAttribute("menuItems", menuRepo.findAll());

        if (result.hasErrors()) {
            return "order";
        }

        // Parse quantities: param names are qty_<menuItemId>
        Map<Long, Integer> quantities = new HashMap<>();
        for (Map.Entry<String, String> e : allParams.entrySet()) {
            if (e.getKey().startsWith("qty_")) {
                try {
                    Long id  = Long.parseLong(e.getKey().substring(4));
                    int  qty = Integer.parseInt(e.getValue());
                    if (qty > 0) quantities.put(id, qty);
                } catch (NumberFormatException ignored) {}
            }
        }

        try {
            Order saved = orderService.placeOrder(order, quantities);
            model.addAttribute("confirmedOrder", saved);
            return "order-confirmation";
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", "❌ " + ex.getMessage());
            return "order";
        }
    }
}
