package com.limai.service;

public interface RedisService {
    public void set(String key, Object value);
    public void set(String key, Object value, long expireTime);
    public boolean exists(final String key);
    public <T> T get(String key, Class<T> clazz);
    public String get(String key);
    public void delete(String key);
    public Object getObject(String key);
    public boolean setObject(String key,Object object);
    public boolean setObject(String key,Object object,Long expireTime);
}
