package br.com.fiap.reservarestaurante.domain;

import java.util.UUID;

public class Category extends Entity<String> {

    private String name;

    public Category(String name) {
        super(UUID.randomUUID().toString());
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (name.length() < 3)
            throw new IllegalArgumentException("Name must be at least 3 characters");

        this.name = name;
    }
}
