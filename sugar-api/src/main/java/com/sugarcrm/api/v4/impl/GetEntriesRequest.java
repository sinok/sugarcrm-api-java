package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seb on 27/07/16.
 */
public class GetEntriesRequest implements SugarRequest{
    public GetEntriesRequest(String session, String moduleName, List<String> ids) {
        this.session = session;
        this.moduleName = moduleName;
        this.ids = ids;
    }

    protected String session;

    @SerializedName("module_name")
    protected String moduleName;

    protected List<String> ids;

}
