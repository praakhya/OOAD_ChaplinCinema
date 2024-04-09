package com.pes.chaplincinemabackend.permissions;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.auth.services.UserService;
import com.pes.chaplincinemabackend.entities.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserPermissions {
    private final UserService userService;

    public boolean authorizedForAdminAndCurrentUsername(User principal, String username) {
        log.error(String.format("Called User Permissions %s %s", principal, username));
        if (principal.getGrantedAuthorities().contains(Role.ADMIN)) {
            return true;
        }
        else {
            return principal.getUsername().equalsIgnoreCase(username);
        }
    }
    public boolean authorizedForAdminAndCurrentId(User principal, UUID id) {
        log.error(String.format("Called User Permissions %s %s", principal, id.toString()));
        if (principal.getGrantedAuthorities().contains(Role.ADMIN)) {
            return true;
        }
        else {
            User u = userService.findOneByID(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
            return principal.getUsername().equalsIgnoreCase(u.getUsername());
        }
    }
    public boolean authorizedForAdminAndCurrentCustomerBody(User principal, Customer customer) {
        log.error(String.format("Called User Permissions %s %s", principal, customer));
        if (principal.getGrantedAuthorities().contains(Role.ADMIN)) {
            return true;
        }
        else {
            return principal.getUsername().equalsIgnoreCase(customer.getUsername());
        }
    }
    public boolean authorizedForAdminAndCurrentUser(List<User> users) {
        users.forEach(u -> u.setPassword(null));
        return true;
    }
}