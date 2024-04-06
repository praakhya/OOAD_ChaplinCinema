package com.pes.chaplincinemabackend.auth.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pes.chaplincinemabackend.common.entities.AbstractBase;
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
public class User extends AbstractBase implements UserDetails {
    public User() {
        this.id = UUID.randomUUID();
    }
    private String profilePictureURL = "/images/placeholderProfile.jpeg";
    private String username;

    private String password;
    @Id
    private UUID id;
    private Set<Role> grantedAuthorities;
    private boolean expired = false;
    private boolean locked = false;
    private boolean credentialsExpired = false;
    private boolean enabled = false;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities.stream().map(Role::getAuthority).collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }
}
