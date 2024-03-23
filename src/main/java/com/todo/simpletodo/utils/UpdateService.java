package com.todo.simpletodo.utils;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class UpdateService<T> {
    public T updateObject(T existingObject, T partialObject) throws IllegalAccessException {
        Class<?> clazz = existingObject.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields

            Object existingValue = field.get(existingObject);
            Object updatedValue = field.get(partialObject);

            if (updatedValue != null) {
                field.set(existingObject, updatedValue);
            }
        }
        return existingObject;
    }
}
