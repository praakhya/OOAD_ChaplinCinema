package com.pes.chaplincinemabackend.auth.pojos;

public record ChangePassword(String username, String password, String reenterPassword) {
}