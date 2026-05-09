package com.lemaison.leMaison;

import com.lemaison.leMaison.model.MenuItem;
import com.lemaison.leMaison.repository.MenuItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LeMaisonApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeMaisonApplication.class, args);
    }

    @Bean
    CommandLineRunner seedMenu(MenuItemRepository menuRepo) {
        return args -> {
            if (menuRepo.count() == 0) {
                menuRepo.save(new MenuItem("French Fries",       "Crispy golden fries",                    50.0));
                menuRepo.save(new MenuItem("Steak 250g",         "Grilled premium beef steak",             300.0));
                menuRepo.save(new MenuItem("Ratatouille",        "Classic French vegetable medley",        125.0));
                menuRepo.save(new MenuItem("White Rice Plate",   "Fluffy white rice with garnish",         100.0));
                menuRepo.save(new MenuItem("Red Pasta",          "Pasta in rich tomato sauce",             100.0));
                menuRepo.save(new MenuItem("Pizza Margherita L", "Large margherita with fresh basil",      200.0));
            }
        };
    }
}
