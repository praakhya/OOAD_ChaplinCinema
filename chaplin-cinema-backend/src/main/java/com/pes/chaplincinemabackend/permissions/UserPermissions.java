package com.pes.chaplincinemabackend.permissions;

import com.pes.chaplincinemabackend.auth.entities.Role;
import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserPermissions {
    private final UserService userService;

    public boolean authorizedForAdminAndCurrentCustomer(User principal, String username) {
        log.error(String.format("Called User Permissions %s %s", principal, username));
        if (principal.getGrantedAuthorities().contains(Role.ADMIN)) {
            return true;
        }
        else if (principal.getGrantedAuthorities().contains(Role.CUSTOMER)) {

            return principal.getUsername().equalsIgnoreCase(username);
        }
        return false;
    }
    public boolean authorizedForAdminAndCurrentUser(List<User> users) {
        users.forEach(u -> u.setPassword(null));
        return true;
    }
}