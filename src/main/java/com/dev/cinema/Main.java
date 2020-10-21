package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    private static final MovieService movieService =
            context.getBean(MovieService.class);
    private static final MovieSessionService sessionService =
            context.getBean(MovieSessionService.class);
    private static final CinemaHallService cinemaHallService =
            context.getBean(CinemaHallService.class);
    private static final OrderService orderService =
            context.getBean(OrderService.class);
    private static final ShoppingCartService shoppingCartService =
            context.getBean(ShoppingCartService.class);
    private static final AuthenticationService authService =
            context.getBean(AuthenticationService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("IMAX");
        cinemaHallService.add(cinemaHall);

        MovieSession session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(movie);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);

        User user = authService.register("test@test.com", "1234");
        try {
            authService.login(user.getEmail(), user.getPassword());
            logger.info("A user logged in");
        } catch (AuthenticationException e) {
            logger.warn("Login failed with exception ", e);
        }

        shoppingCartService.addSession(session, user);
        logger.info("Trying to get shopping cart for user " + shoppingCartService.getByUser(user));

        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
        logger.info("Trying to get all user's tickets " + shoppingCartService.getByUser(user));
        logger.info("Trying to get order history " + orderService.getOrderHistory(user));
    }
}
