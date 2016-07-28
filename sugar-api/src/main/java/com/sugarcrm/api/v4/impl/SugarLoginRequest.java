package com.sugarcrm.api.v4.impl;

import com.sugarcrm.api.SugarCredentials;
import com.sugarcrm.api.SugarRequest;

public class SugarLoginRequest implements SugarRequest{
    protected SugarCredentials user_auth;
    public void setUserAuth(SugarCredentials auth){
      user_auth = auth;
    }

    public SugarLoginRequest(SugarCredentials user_auth) {
        this.user_auth = user_auth;
    }
}