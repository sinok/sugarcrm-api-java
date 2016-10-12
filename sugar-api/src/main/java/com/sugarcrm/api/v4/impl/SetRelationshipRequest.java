package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by nico  on 13/10/2016.
 */
public class SetRelationshipRequest implements SugarRequest{


    public SetRelationshipRequest(String session, String moduleName, String module_id, String link_field_name, List<String> related_ids, List<String> name_value_list, Integer delete){
        this.session = session;
        this.moduleName = moduleName;
        this.module_id = module_id;
        this.link_field_name = link_field_name;
        this.related_ids = related_ids;
        this.name_value_list = name_value_list;
        this.delete = delete;
    }

    protected String session;
    @SerializedName("module_name")
    protected String moduleName;

    protected String module_id;
    protected String link_field_name;
    protected List<String> related_ids;
    protected List<String> name_value_list;
    protected Integer delete;

}
