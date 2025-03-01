package br.com.fiap.reservarestaurante.domain;

public abstract class Entity<T> {
    private final T id;

    protected Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
