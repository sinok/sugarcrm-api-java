package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

public class GetEntryRequest implements SugarRequest{
    
    public GetEntryRequest(String session, String moduleName, String id){
      this.session = session;
      this.moduleName = moduleName;
      this.id = id;
    }
    
    protected String session;
    
    @SerializedName("module_name")
    protected String moduleName;
    
    protected String id;
    
  }
