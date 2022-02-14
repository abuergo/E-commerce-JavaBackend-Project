package com.finalproject.Ecommerce.cache;

public interface CacheClient<T> {
    void save(String key, T data);
    T recover(String key, Class<T> classValue);
}
