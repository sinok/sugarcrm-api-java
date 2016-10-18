package com.sugarcrm.api.v4.impl;

import com.sugarcrm.api.SugarCredentials;
import com.sugarcrm.api.SugarRequest;
import com.sugarcrm.api.SugarSession;

public class SugarLogoutRequest implements SugarRequest{
    protected SugarSession session;

    public SugarLogoutRequest(SugarSession session) {
        this.session = session;
    }
}