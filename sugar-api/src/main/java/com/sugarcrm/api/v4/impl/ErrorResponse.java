package com.sugarcrm.api.v4.impl;

/**
 * Error response from the Sugar v4 API
 *
 * @author mmarum
 */
public class ErrorResponse {

    protected String name;
    protected Integer number;
    protected String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }
}
