package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

/**
 * Created by seb on 29/07/16.
 */
public class GetDocumentRevisionRequest implements SugarRequest{

    public GetDocumentRevisionRequest(String session, String revisionId) {
        this.session = session;
        this.revisionId = revisionId;
    }
    protected String session;
    @SerializedName("i")
    protected String revisionId;
}
