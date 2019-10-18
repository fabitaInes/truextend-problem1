package com.truextend.problem1.exception;

/**
 * The type Clazz not found exception.
 */
public class ClazzNotFoundException extends Exception {
    /**
     * Instantiates a new Clazz not found exception.
     *
     * @param code the code
     */
    public ClazzNotFoundException(String code) {
        super(String.format("Class with code %s not found", code));
    }
}
