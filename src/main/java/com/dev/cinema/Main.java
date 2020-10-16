package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
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
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    private static final Logger logger = Logger.getLogger(Main.class);
    private static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService sessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final UserService userService
            = (UserService) injector.getInstance(UserService.class);
    private static final ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static final OrderService orderService
            = (OrderService) injector.getInstance(OrderService.class);
    private static final AuthenticationService authService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);

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

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("1234");
        userService.add(user);

        authService.register(user.getEmail(), user.getPassword());
        try {
            authService.login(user.getEmail(), user.getPassword());
            logger.info("A user logged in");
        } catch (AuthenticationException e) {
            logger.warn("Login failed with exception ", e);
        }

        shoppingCartService.registerNewShoppingCart(user);
        shoppingCartService.addSession(session, user);
        logger.info("Trying to get shopping cart for user " + shoppingCartService.getByUser(user));

        orderService.completeOrder(shoppingCartService.getByUser(user).getTickets(), user);
        logger.info("Trying to get all user's tickets " + shoppingCartService.getByUser(user));
        logger.info("Trying to get order history " + orderService.getOrderHistory(user));
    }
}
