# LeMaison Restaurant

A full-stack restaurant web application built with Spring Boot, developed as a team project. The app allows customers to browse a menu, place orders, and make table reservations.

> This repository is a fork of a collaborative team project. The original repository is [yelsebety/LeMaison-Restaurant](https://github.com/yelsebety/LeMaison-Restaurant). I contributed 11 commits to the codebase as part of the team.

## Features

- **Menu browsing** — view available dishes and their details
- **Order placement** — add items to an order and confirm it, with an order confirmation page
- **Table reservations** — book a table through a dedicated reservation form
- **Home page** — landing page for navigating the app

## Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC, Spring Data JPA (Hibernate)
- **Frontend:** Thymeleaf templates
- **Database:** MySQL
- **Build tool:** Maven

## Architecture

The project follows a standard layered Spring Boot architecture:

```
Controller  →  Service  →  Repository  →  Model
```

- **Controllers:** `HomeController`, `MenuController`, `OrderController`, `ReservationController`
- **Services:** `OrderService`, `ReservationService`
- **Repositories:** `MenuItemRepository`, `OrderRepository`, `OrderItemRepository`, `ReservationRepository`
- **Models:** `MenuItem`, `Order`, `OrderItem`, `Reservation`

## How to run

1. Install MySQL locally and create a database named `restaurantdb`
2. Update `src/main/resources/application.properties` with your own MySQL username and password
3. Run the application using Maven:
```
mvn spring-boot:run
```
4. The app will be available at `http://localhost:8080`

Database tables are created and updated automatically via Hibernate (`spring.jpa.hibernate.ddl-auto=update`) — no manual schema setup needed beyond creating the empty database.

## Team Project Note

This was built collaboratively as part of a university course project. It reflects hands-on practice with a full Spring Boot MVC stack — layered architecture, JPA/Hibernate persistence, and server-rendered Thymeleaf views — applied to a realistic multi-feature application rather than a single isolated exercise.
