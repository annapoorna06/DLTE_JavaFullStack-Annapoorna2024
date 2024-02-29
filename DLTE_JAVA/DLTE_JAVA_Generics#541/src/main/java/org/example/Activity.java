package org.example;

public interface Activity<T> {
    T create(T object);
    T read(int id);
    void update(int id, T updatedObject);
    void delete(int id);
}
