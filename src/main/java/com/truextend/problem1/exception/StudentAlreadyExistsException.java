package com.truextend.problem1.exception;

/**
 * The type Student already exists exception.
 */
public class StudentAlreadyExistsException extends Exception {
    /**
     * Instantiates a new Student already exists exception.
     *
     * @param id the id
     */
    public StudentAlreadyExistsException(Long id) {
        super(String.format("Student with id %s already exists", id));
    }
}
