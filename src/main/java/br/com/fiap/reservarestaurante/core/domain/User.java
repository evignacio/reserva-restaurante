package br.com.fiap.reservarestaurante.core.domain;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User extends Entity<String> {
    private String name;
    private String surname;
    private int age;
    private String email;

    public User(String name, String surname, int age, String email) {
        super(UUID.randomUUID().toString());
        setName(name);
        setSurname(surname);
        setAge(age);
        setEmail(email);
    }

    public User(String id, String name, String surname, int age, String email) {
        super(id);
        setName(name);
        setSurname(surname);
        setAge(age);
        setEmail(email);
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid e-mail, should be [A-Za-z0-9+_.-]+@[A-Za-z0-9.-]");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return email;
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
