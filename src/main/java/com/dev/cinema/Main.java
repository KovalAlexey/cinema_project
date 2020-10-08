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
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");

        CinemaHall red = new CinemaHall();
        red.setCapacity(100);
        red.setDescription("IMAX grand hall");

        LocalDateTime today = LocalDateTime.now();
        MovieSession premierSession = new MovieSession();
        premierSession.setCinemaHall(red);
        premierSession.setLocalDateTime(today);
        premierSession.setMovie(movie);

        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieService.add(movie);
        cinemaHallService.add(red);
        movieSessionService.add(premierSession);

        movieService.getAll().forEach(System.out::println);
        cinemaHallService.getAll().forEach(System.out::println);
        movieSessionService.findAvailableSessions(1L, today.toLocalDate())
                .forEach(System.out::println);

        AuthenticationService authService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("1234");
        authService.register(user.getEmail(), user.getPassword());
        authService.login(user.getEmail(), user.getPassword());
        UserService userService = (UserService) injector.getInstance(UserService.class);
        System.out.println(userService.findByEmail(user.getEmail()).get());
    }
}
