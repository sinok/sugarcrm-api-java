package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.List;

/**
 * Created by seb on 28/07/16.
 */
public class GetModuleFieldsRequest implements SugarRequest {

    public GetModuleFieldsRequest(String session, String moduleName) {
        this.session = session;
        this.moduleName = moduleName;
    }

    public GetModuleFieldsRequest(String session, String moduleName, List<String> fields) {
        this.session = session;
        this.moduleName = moduleName;
        this.fields = fields;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    protected String session;

    @SerializedName("module_name")
    protected String moduleName;
    protected List<String> fields;

}
