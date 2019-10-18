package com.truextend.problem1.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * The type Clazz.
 */
public class Clazz {
    @ApiModelProperty(notes = "Class code")
    private String code;
    @ApiModelProperty(notes = "Class tittle")
    private String tittle;
    @ApiModelProperty(notes = "Class description")
    private String description;

    /**
     * Instantiates a new Clazz.
     */
    public Clazz() {
    }

    /**
     * Instantiates a new Clazz.
     *
     * @param code        the code
     * @param tittle      the tittle
     * @param description the description
     */
    public Clazz(String code, String tittle, String description) {
        this.code = code;
        this.tittle = tittle;
        this.description = description;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets tittle.
     *
     * @return the tittle
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * Sets tittle.
     *
     * @param tittle the tittle
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
