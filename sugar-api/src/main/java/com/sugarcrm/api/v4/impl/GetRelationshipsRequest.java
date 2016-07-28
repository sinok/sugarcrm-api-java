package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.List;

/**
 * Created by seb on 27/07/16.
 */
public class GetRelationshipsRequest implements SugarRequest {

    public GetRelationshipsRequest(String session, String moduleName, String id, String target, List<String> targetFields) {
        this.session = session;
        this.moduleName = moduleName;
        this.id = id;
        this.target = target;
        this.targetFields = targetFields;
    }

    protected String session;

    @SerializedName("module_name")
    protected String moduleName;

    @SerializedName("module_id")
    protected String id;

    @SerializedName("link_field_name")
    protected String target;

    protected Boolean deleted = Boolean.FALSE;

    @SerializedName("related_fields")
    protected List<String> targetFields;

}
