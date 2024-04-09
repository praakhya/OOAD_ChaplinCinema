package com.pes.chaplincinemabackend.auth.pojos;
import com.pes.chaplincinemabackend.auth.entities.Role;

import java.util.Set;
import java.util.UUID;

public record User(
        String username,
        String profilePictureURL,
        UUID id,
        Set<Role> grantedAuthorities,
        AuthToken authToken
) {
}