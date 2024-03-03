package com.pes.chaplincinemabackend.auth.annotations;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('REFRESH')")
public @interface RefreshRolePermission {
}