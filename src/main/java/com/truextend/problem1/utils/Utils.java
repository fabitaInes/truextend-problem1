package com.truextend.problem1.utils;

/**
 * The type Utils.
 */
public class Utils {

    private static String clazzStudentId = "%s|%d";

    /**
     * Gets clazz student id.
     *
     * @param code the code
     * @param id   the id
     * @return the clazz student id
     */
    public static String getClazzStudentId(String code, Long id) {
        return String.format(clazzStudentId, code, id);
    }

}
