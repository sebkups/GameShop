package com.example.shop.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class entityClass, Long id) {
        super("Entity of class " + entityClass.getSimpleName() + " with id " + id + " not found");
    }
}
