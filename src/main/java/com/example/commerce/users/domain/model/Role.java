package com.example.commerce.users.domain.model;

import java.util.UUID;

public class Role {

    private UUID id;
    private String name;

    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}