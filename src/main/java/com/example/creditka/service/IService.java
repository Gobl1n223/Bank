package com.example.creditka.service;

import java.util.List;
import java.util.UUID;

public interface IService<T>
{
    List<T> getAll();
    void delete(T obj);
    T create(T obj);
    T getByUuid(UUID uuid);
}
