package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;

/**
 * GetEntry response for v4 API
 *
 * @author mmarum
 */
public class GetModuleFieldsResponse implements SugarResponse{

    @SerializedName("module_fields")
    protected SugarBean[] module_fields;
    @SerializedName("link_fields")
    protected SugarBean[] link_fields;
    @SerializedName("module_name")
    protected String module_name;

    public SugarBean[] getEntryList() {
        return module_fields;
    }
}
