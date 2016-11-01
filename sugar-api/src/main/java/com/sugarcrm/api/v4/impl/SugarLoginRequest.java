package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarCredentials;
import com.sugarcrm.api.SugarRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SugarLoginRequest implements SugarRequest{
    protected SugarCredentials user_auth;
    public void setUserAuth(SugarCredentials auth){
      user_auth = auth;
    }

    protected String application_name;

    @SerializedName("name_value_list")
    protected List<Map<String, Object>> name_value_list;

    public SugarLoginRequest(SugarCredentials user_auth) {

        this.user_auth = user_auth;

        this.application_name = "hello";

        this.name_value_list = new ArrayList<Map<String, Object>>();
        Map<String, Object> language = new HashMap<String, Object>();
        language.put("name", "language");
        language.put("value", "fr_FR");
        this.name_value_list.add(language);
    }
}