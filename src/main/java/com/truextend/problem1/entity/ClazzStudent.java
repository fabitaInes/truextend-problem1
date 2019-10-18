package com.truextend.problem1.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * The type Clazz student.
 */
public class ClazzStudent {

    @ApiModelProperty(notes = "Asignee id")
    private String id;
    @ApiModelProperty(notes = "Asignee class code")
    private String clazzCode;
    @ApiModelProperty(notes = "Asignee student identification")
    private Long studentId;

    /**
     * Instantiates a new Clazz student.
     *
     * @param id        the id
     * @param clazzCode the clazz code
     * @param studentId the student id
     */
    public ClazzStudent(String id, String clazzCode, Long studentId) {
        this.id = id;
        this.clazzCode = clazzCode;
        this.studentId = studentId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets clazz code.
     *
     * @return the clazz code
     */
    public String getClazzCode() {
        return clazzCode;
    }

    /**
     * Sets clazz code.
     *
     * @param clazzCode the clazz code
     */
    public void setClazzCode(String clazzCode) {
        this.clazzCode = clazzCode;
    }

    /**
     * Gets student id.
     *
     * @return the student id
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * Sets student id.
     *
     * @param studentId the student id
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
