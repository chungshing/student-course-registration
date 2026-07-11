package com.example.registration.exception;

public class DuplicateRegistrationException extends RuntimeException {
    public DuplicateRegistrationException(Long studentId, Long courseId) {
        super("Student " + studentId + " is already registered for course " + courseId);
    }
}
