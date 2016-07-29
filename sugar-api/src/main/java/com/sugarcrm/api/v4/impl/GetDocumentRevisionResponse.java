package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;

/**
 * Created by seb on 29/07/16.
 */
public class GetDocumentRevisionResponse implements SugarResponse {

    @SerializedName("document_revision")
    protected DocumentRevision document;

    public DocumentRevision getDocument() {
        return document;
    }
}
