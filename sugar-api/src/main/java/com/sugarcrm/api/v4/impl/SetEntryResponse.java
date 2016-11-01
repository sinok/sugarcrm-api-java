package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by seb on 28/07/16.
 */
public class SetEntryResponse implements SugarResponse {

    protected String id;

    @SerializedName("entry_list")
    protected SugarBean entry;

    public String getId() {
        return id;
    }

    public com.sugarcrm.api.SugarBean getEntry() {
        return entry;
    }
}
