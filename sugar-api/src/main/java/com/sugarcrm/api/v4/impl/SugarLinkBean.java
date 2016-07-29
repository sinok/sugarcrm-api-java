package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by seb on 29/07/16.
 */
public class SugarLinkBean {

    public SugarLinkBean() {
    }

    public List<String> getFields() {
        return fields;
    }

    public String getLinkName() {
        return linkName;
    }

    @SerializedName("name")
    protected String linkName;
    @SerializedName("value")
    protected List<String> fields;
}
