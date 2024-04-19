package com.pes.chaplincinemabackend.auth.pojos;
import com.pes.chaplincinemabackend.auth.entities.Role;

import java.util.Collection;

public record AuthToken(String username, String profilePictureURL, String authToken, String refreshToken, Collection<Role> authTokenRoles) {
}