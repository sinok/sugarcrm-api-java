package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;

/**
 * GetEntry response for v4 API
 *
 * @author mmarum
 */
public class GetEntryResponse implements SugarResponse{

    @SerializedName("entry_list")
    protected SugarBean[] entryList;

    public SugarBean[] getEntryList() {
        return entryList;
    }
}
