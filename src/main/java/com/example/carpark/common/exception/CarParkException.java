package com.example.carpark.common.exception;

public class CarParkException {
    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class EntityAlreadyExistsException extends RuntimeException {
        public EntityAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class CommonLogicExeption extends RuntimeException {
        public CommonLogicExeption(String message) {
            super(message);
        }
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType) {
        switch (exceptionType) {
            case ENTITY_NOT_FOUND:
                return new EntityNotFoundException(entityType + " not found");
            case ENTITY_ALREADY_EXISTS:
                return new EntityAlreadyExistsException(entityType + " already exists");
            default:
                return new RuntimeException("Unexpected exception type");
        }
    }
}
