package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private static final String ADMIN_ROLE_NAME = "ADMIN";
    private static final String USER_ROLE_NAME = "USER";
    private static final Logger log = Logger.getLogger(InjectData.class);
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    public InjectData(UserService userService,
                      ShoppingCartService shoppingCartService,
                      RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void addAdminAndRoles() {
        log.info("Injecting all roles...");
        Role userRole = Role.of(USER_ROLE_NAME);
        Role adminRole = Role.of(ADMIN_ROLE_NAME);
        roleService.add(userRole);
        roleService.add(adminRole);
        log.info("Roles added successfully!");
        log.info("ADMIN injecting...");
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("1234");
        admin.setRoles(Set.of(adminRole));
        admin = userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
        log.info("ADMIN injected successfully!");
    }
}
