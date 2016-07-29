package com.sugarcrm.api;

import com.sugarcrm.api.v4.impl.*;

import java.util.List;
import java.util.Map;


/**
 * Sugar Client
 *
 * @author mmarum
 */
public class SugarClient {

    public static final String SUGAR_API_VERSION_4_1 = "v4_1";
    public static final String SUGAR_API_VERSION_4 = "v4";

    private SugarApi sugar = null;

    public SugarClient(String sugarUrl, String sugarVersion) {
        //Only support 1 version of the API right now
        sugar = new SugarApi(sugarUrl, sugarVersion);
    }

    public SugarSession getSugarSession(String userId, String password) throws SugarApiException {
        return sugar.getSugarSession(new SugarCredentials(userId, password));
    }

    public SugarSession getSugarSession(SugarCredentials credentials) throws SugarApiException {
        return sugar.getSugarSession(credentials);
    }

    public SugarBean getBean(SugarSession session, String moduleName, String guid) throws SugarApiException {
        return sugar.getBean(session, moduleName, guid);
    }

    public List<SugarBean> getBeansById(SugarSession session, String moduleName, List<String> uuids) throws SugarApiException {
        return sugar.getBeansById(session, moduleName, uuids);
    }

    public List<SugarBean> getBeans(SugarSession session, String moduleName, String query) throws SugarApiException {
        return sugar.getBeans(session, moduleName, query);
    }

    public List<SugarBean> getBeans(GetEntryListRequest request) throws SugarApiException {
        return sugar.getBeans(request);
    }

    public SetEntryResponse setEntry(SugarSession session, String moduleName, List<Map<String,Object>> entryData) throws SugarApiException {
        return sugar.setEntry(session,moduleName,entryData);
    }

    public SetEntriesResponse setEntries(SugarSession session, String moduleName, List<List<Map<String,Object>>> entriesData) throws SugarApiException {
        return sugar.setEntries(session,moduleName,entriesData);
    }

    public List<SugarBean> getRelationships(SugarSession session, String moduleName, String id, String target, List<String> targetFields) throws SugarApiException {
        return sugar.getRelationships(session, moduleName, id, target, targetFields);
    }

    public DocumentRevision getDocumentRevision(SugarSession session, String revisionId) throws SugarApiException {
        return sugar.getDocumentRevision(session, revisionId);
    }
}
