package com.truextend.problem1.exception;

/**
 * The type Clazz student not found exception.
 */
public class ClazzStudentNotFoundException extends Exception {
    /**
     * Instantiates a new Clazz student not found exception.
     *
     * @param code the code
     * @param id   the id
     */
    public ClazzStudentNotFoundException(String code, Long id) {
        super(String.format("Class with code %s assigned to student with id %d not found", code, id));
    }
}
