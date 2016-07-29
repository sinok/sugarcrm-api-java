package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by seb on 28/07/16.
 */
public class SetEntryRequest implements SugarRequest{

    public SetEntryRequest(String session, String moduleName,  List<Map<String, Object>> nameValueList){
        this.session = session;
        this.moduleName = moduleName;
        this.nameValueList = nameValueList;
    }

    protected String session;
    @SerializedName("module_name")
    protected String moduleName;
    @SerializedName("name_value_list")
    protected List<Map<String, Object>> nameValueList;
}
