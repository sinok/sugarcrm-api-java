package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;

/**
 * Created by seb on 29/07/16.
 */
public class DocumentRevision {

    public String getId() {
        return id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getContent() {
        return content;
    }

    protected String id;
    @SerializedName("document_name")
    protected String documentName;
    @SerializedName("revision")
    protected String revisionNumber;
    protected String filename;
    @SerializedName("file")
    protected byte[] content;
}
