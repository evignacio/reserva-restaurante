package br.com.fiap.reservarestaurante.core.domain;

import java.util.UUID;

public class User extends Entity<String> {
    private String name;
    private String surname;
    private int age;

    public User(String name, String surname, int age) {
        super(UUID.randomUUID().toString());
        setName(name);
        setSurname(surname);
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18)
            throw new IllegalArgumentException("Age must be at least 18");

        if (age > 100)
            throw new IllegalArgumentException("Age must be at most 100");

        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty())
            throw new IllegalArgumentException("Surname cannot be null or empty");

        if (surname.length() < 3)
            throw new IllegalArgumentException("Surname must be at least 3 characters");

        this.surname = surname;
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

    public String getFullName() {
        return name + " " + surname;
    }
}
