package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;

/**
 * SugarBean response for the v4 API
 *
 * @author mmarum
 */
public class SugarBean implements com.sugarcrm.api.SugarBean {


    private final static Logger LOG = LoggerFactory.getLogger(SugarBean.class);

    @SerializedName("name_value_list")
    protected HashMap<String, HashMap<String, String>> values;

    @SerializedName("module_name")
    protected String moduleName;

    @SerializedName("id")
    protected String id;

    //Needed for Gson
    public SugarBean() {
        LOG.info("SugarBean created (empty contructor)");
    }

    public SugarBean(HashMap<String, HashMap<String, String>> name_value_list, String module_name) {
        values = name_value_list;
        moduleName = module_name;
    }

    public SugarBean(String id, HashMap<String, HashMap<String, String>> name_value_list, String module_name) {
        values = name_value_list;
        this.id = id;
        moduleName = module_name;
    }

//    public SugarBean(HashMap<String, String> id, HashMap<String, HashMap<String, String>> name_value_list, String module_name) {
//        values = name_value_list;
//        this.id = id.get("id");
//        moduleName = module_name;
//    }

    public String getId() {
        return id;
    }

    public String get(String fieldName) {
        return (values.get(fieldName) == null ) ? null : values.get(fieldName).get("value");
    }

    public Collection<String> getFieldNames() {
        return values.keySet();
    }

    public String getModuleName() {
        return moduleName;
    }

    @Override
    public String toString() {
        return "SugarBean{" +
                "values=" + values +
                ", moduleName='" + moduleName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
