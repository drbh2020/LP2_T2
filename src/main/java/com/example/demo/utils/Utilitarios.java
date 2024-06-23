package com.example.demo.utils;

import java.lang.reflect.Field;

import jakarta.persistence.Entity;

public class Utilitarios {
	public static <T> boolean areAllFieldsNull(T obj) {
		
		if (obj == null) {
			return true;
        }
		
		 if (!obj.getClass().isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("La clase " + obj.getClass().getName() + " no es un @Entity");
        }
		
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    } 
}
