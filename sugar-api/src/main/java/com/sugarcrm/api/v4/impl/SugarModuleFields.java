package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.HashMap;

/**
 * SugarBean response for the v4 API
 *
 * @author mmarum
 */
public class SugarModuleFields {

    @SerializedName("module_fields")
    protected HashMap<String, HashMap<String, Object>> moduleFields;

    @SerializedName("link_fields")
    protected HashMap<String, HashMap<String, Object>> linkFields;

    @SerializedName("module_name")
    protected String moduleName;

    //Needed for Gson
    public SugarModuleFields() {

    }

    public SugarModuleFields(String module_name, HashMap<String, HashMap<String, Object>> module_fields, HashMap<String, HashMap<String, Object>> link_fields) {
        moduleFields = module_fields;
        linkFields = link_fields;
        moduleName = module_name;
    }


    public HashMap<String, Object> getModuleField(String fieldName) {
//        return (moduleFields.get(fieldName) == null ) ? null : moduleFields.get(fieldName).get("value");
        return (moduleFields.get(fieldName) == null ) ? null : moduleFields.get(fieldName);
    }

    public HashMap<String, Object> getLinkField(String fieldName) {
//        return (moduleFields.get(fieldName) == null ) ? null : moduleFields.get(fieldName).get("value");
        return (linkFields.get(fieldName) == null ) ? null : linkFields.get(fieldName);
    }

    public Collection<String> getModuleFieldNames() {
        return moduleFields.keySet();
    }
    public Collection<String> getLinkFieldNames() {
        return linkFields.keySet();
    }

    public String getModuleName() {
        return moduleName;
    }

    @Override
    public String toString() {
        return "SugarFields{" +
                "moduleFields=" + moduleFields +
                "linkFields=" + linkFields +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
