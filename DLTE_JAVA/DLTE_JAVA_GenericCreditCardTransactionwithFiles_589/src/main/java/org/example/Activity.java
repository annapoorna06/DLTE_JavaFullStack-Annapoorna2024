package org.example;

public interface Activity<T> {
    T create(T object);
}
