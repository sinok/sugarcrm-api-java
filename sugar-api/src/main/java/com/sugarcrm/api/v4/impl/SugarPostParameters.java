package com.sugarcrm.api.v4.impl;

import com.sugarcrm.api.SugarRequest;

/**
 * Created by seb on 27/07/16.
 */
public class SugarPostParameters {
    protected String method;
    protected String inputType = "json";
    protected String responseType  = "json";
    protected SugarRequest restData;


    public String method() {
        return method;
    }

    public SugarPostParameters method(String method) {
        this.method = method;
        return this;
    }

    public String inputType() {
        return inputType;
    }

    public SugarPostParameters inputType(String inputType) {
        this.inputType = inputType;
        return this;
    }

    public String responseType() {
        return responseType;
    }

    public SugarPostParameters responseType(String responseType) {
        this.responseType = responseType;
        return this;
    }

    public SugarRequest restData() {
        return restData;
    }

    public SugarPostParameters restData(SugarRequest restData) {
        this.restData = restData;
        return this;
    }
}
