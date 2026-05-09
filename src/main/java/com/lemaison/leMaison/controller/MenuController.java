package com.lemaison.leMaison.controller;

import com.lemaison.leMaison.repository.MenuItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    private final MenuItemRepository menuRepo;

    public MenuController(MenuItemRepository menuRepo) {
        this.menuRepo = menuRepo;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("items", menuRepo.findAll());
        return "menu";
    }
}
