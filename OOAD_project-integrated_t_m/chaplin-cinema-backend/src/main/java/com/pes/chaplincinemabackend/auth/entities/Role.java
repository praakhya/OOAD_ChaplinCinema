package com.pes.chaplincinemabackend.auth.entities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public enum Role {

    USER("USER"),
    REFRESH("REFRESH"), //for refreshing auth token
    ADMIN("ADMIN"),
    THEATRE_ADMIN("THEATRE_ADMIN"),
    CUSTOMER("CUSTOMER");
    Role(String authority) {
        this.authority = new SimpleGrantedAuthority(authority);
    }
    public GrantedAuthority getAuthority() {
        return authority;
    }
    private final SimpleGrantedAuthority authority;
}