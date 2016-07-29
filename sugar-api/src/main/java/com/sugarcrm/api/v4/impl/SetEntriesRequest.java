package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by seb on 28/07/16.
 */
public class SetEntriesRequest implements SugarRequest{

    public SetEntriesRequest(String session, String moduleName, List<List<Map<String, Object>>> nameValueLists){
        this.session = session;
        this.moduleName = moduleName;
        this.nameValueLists = nameValueLists;
    }

    protected String session;
    @SerializedName("module_name")
    protected String moduleName;
    @SerializedName("name_value_lists")
    List<List<Map<String, Object>>> nameValueLists;
}
