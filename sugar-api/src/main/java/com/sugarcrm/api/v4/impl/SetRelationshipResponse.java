package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;

/**
 * Created by nico on 13/10/2016.
 */
public class SetRelationshipResponse implements SugarResponse {

    protected Integer created;
    protected Integer failed;
    protected Integer deleted;

    public Integer getCreated() {
        return created;
    }

    public Integer getFailed() {
        return failed;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public String toString(){
        String stringed = "Created : " + getCreated().toString() + ". Failed : " + getFailed().toString() + ". Deleted : " + getDeleted().toString() + ".";
        return stringed;
    }
}
