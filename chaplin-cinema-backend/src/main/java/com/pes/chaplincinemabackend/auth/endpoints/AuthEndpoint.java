package com.pes.chaplincinemabackend.auth.endpoints;
import com.pes.chaplincinemabackend.auth.annotations.RefreshRolePermission;
import com.pes.chaplincinemabackend.auth.entities.User;
import com.pes.chaplincinemabackend.auth.pojos.AuthToken;
import com.pes.chaplincinemabackend.auth.pojos.Login;
import com.pes.chaplincinemabackend.auth.services.AuthenticationService;
import com.pes.chaplincinemabackend.common.utils.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping(Paths.V1.Auth.fullPath)
@RequiredArgsConstructor
public class AuthEndpoint {
    private final AuthenticationService authenticateService;
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<com.pes.chaplincinemabackend.auth.pojos.User> login(@RequestBody Login login) {
        return authenticateService.auth(login);
    }
    @RefreshRolePermission
    @RequestMapping(path = Paths.V1.Auth.Refresh, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<AuthToken> refresh(@AuthenticationPrincipal User user) {
        return authenticateService.refresh(user);
    }
}