package com.truextend.problem1.exception;

/**
 * The type Clazz student already exists exception.
 */
public class ClazzStudentAlreadyExistsException extends Exception {
    /**
     * Instantiates a new Clazz student already exists exception.
     *
     * @param code the code
     * @param id   the id
     */
    public ClazzStudentAlreadyExistsException(String code, Long id) {
        super(String.format("Class with code %s already assigned to student with id %d", code, id));
    }
}
