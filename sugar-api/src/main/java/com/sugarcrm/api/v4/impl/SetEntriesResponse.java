package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;

import java.util.List;

/**
 * Created by seb on 28/07/16.
 */
public class SetEntriesResponse implements SugarResponse {

    protected List<String> ids;
    @SerializedName("entry_list")
//    protected SugarBean[] entryList;

    public List<String> getIds() {
        return ids;
    }

//    public SugarBean[] getEntryList() {
//        return entryList;
//    }
}
