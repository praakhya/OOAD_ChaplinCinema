package com.pes.chaplincinemabackend.auth.entities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Document("user")
@Data
public class UserEntity extends AbstractBaseEntity implements UserDetails {
    public UserEntity() {
        this.id = UUID.randomUUID();
    }
    private String profilePictureURL;
    private String username;

    private String password;
    @Id
    private UUID id;
    private Set<Role> grantedAuthorities;
    private boolean expired = false;
    private boolean locked = false;
    private boolean credentialsExpired = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities.stream().map(Role::getAuthority).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
