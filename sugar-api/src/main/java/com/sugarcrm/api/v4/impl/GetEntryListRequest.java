package com.sugarcrm.api.v4.impl;

import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarRequest;

import java.util.List;

/**
 * Created by seb on 28/07/16.
 */
public class GetEntryListRequest implements SugarRequest {

    public GetEntryListRequest(String session, String moduleName) {
        this.session = session;
        this.moduleName = moduleName;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<SugarLinkBean> getLinks() {
        return links;
    }

    public void setLinks(List<SugarLinkBean> links) {
        this.links = links;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    protected String session;

    @SerializedName("module_name")
    protected String moduleName;

    protected String query;
    @SerializedName("order_by")
    protected String order;
    protected Integer offset;
    @SerializedName("select_fields")
    protected List<String> fields;
    @SerializedName("link_name_to_fields_array")
    protected List<SugarLinkBean> links;
    @SerializedName("max_results")
    protected Integer maxResults;
    protected Boolean deleted = Boolean.FALSE;
}
