package com.truextend.problem1.exception;

/**
 * The type Student not found exception.
 */
public class StudentNotFoundException extends Exception {
    /**
     * Instantiates a new Student not found exception.
     *
     * @param id the id
     */
    public StudentNotFoundException(Long id) {
        super(String.format("Student with id %s not found", id));
    }
}
