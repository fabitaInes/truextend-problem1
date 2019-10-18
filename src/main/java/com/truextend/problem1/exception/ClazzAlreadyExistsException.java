package com.truextend.problem1.exception;

/**
 * The type Clazz already exists exception.
 */
public class ClazzAlreadyExistsException extends Exception {
    /**
     * Instantiates a new Clazz already exists exception.
     *
     * @param code the code
     */
    public ClazzAlreadyExistsException(String code) {
        super(String.format("Class with id %s already exists", code));
    }
}
