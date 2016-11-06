package com.sugarcrm.api.v4.impl;

import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sugarcrm.api.*;
import com.sugarcrm.api.SugarBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Sugar API v4 specific stuff
 *
 * @author mmarum
 */
public class SugarApi {


    private final static Logger LOG = LoggerFactory.getLogger(SugarApi.class);

    private String restEndpoint = null;

    private Gson json = null;

    public SugarApi(String sugarUrl, String version) {
        restEndpoint = sugarUrl + "/service/" + version + "/rest.php";
        json = new GsonBuilder().registerTypeHierarchyAdapter(byte[].class,
                new ByteArrayToBase64TypeAdapter()).create();
    }

    public String postToSugar(String urlStr, SugarPostParameters params) throws SugarApiException {
        LOG.debug("JSON rest_data: " + json.toJson(params.restData));
        try {
            HttpResponse<String> result = Unirest.post(urlStr)
                    .field("method", params.method)
                    .field("input_type", params.inputType)
                    .field("response_type", params.responseType)
                    .field("rest_data", json.toJson(params.restData))
                    .asString();

            if (result.getStatus() != 200) {
                throw new SugarApiException(result.getStatusText());
            }

            String response = result.getBody();
            LOG.debug("Response: " + response);
            ErrorResponse err = new SugarResponseValidator(response).getError();
            LOG.debug("Error response: " + err);

            if (err != null) {
                SugarApiException e = new SugarApiException(err.getDescription());
                e.setNumber(err.getNumber());
                e.setName(err.getName());
                throw e;
            }

            return response;
        } catch (UnirestException e) {
            LOG.error("Error while calling the SugarCRM service", e);
            throw new SugarApiException("Error while calling the SugarCRM service", e);
        }
    }

    public SugarSession getSugarSession(SugarCredentials credentials) throws SugarApiException {
        SugarRequest loginReq = new SugarLoginRequest(credentials);
        SugarPostParameters params = new SugarPostParameters().method("login").restData(loginReq);
        return json.fromJson(postToSugar(restEndpoint, params), SugarLoginResponse.class);
    }

    public void closeSugarSession(SugarSession session) throws SugarApiException {
        SugarRequest logoutReq = new SugarLogoutRequest(session);
        SugarPostParameters params = new SugarPostParameters().method("logout").restData(logoutReq);
        postToSugar(restEndpoint, params);
    }

    public SugarModuleFields getModuleFields(SugarSession session, String module, List<String> fields) throws SugarApiException {
        SugarRequest getFieldsReq = new GetModuleFieldsRequest(session.getSessionID(), module, fields);
        SugarPostParameters params = new SugarPostParameters().method("get_module_fields").restData(getFieldsReq);
        SugarModuleFields fieldsResp = json.fromJson(postToSugar(restEndpoint, params), SugarModuleFields.class);
        return fieldsResp;
    }

    public SugarBean getBean(SugarSession session, String moduleName, String uuid) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest req = new GetEntryRequest(sessionId, moduleName, uuid);
        SugarPostParameters params = new SugarPostParameters().method("get_entry").restData(req);
        GetEntryResponse entryResp = json.fromJson(postToSugar(restEndpoint, params), GetEntryResponse.class);
        if (entryResp.getEntryList().length > 0) {
            return entryResp.getEntryList()[0];
        } else {
            return null;
        }
    }

    public List<SugarBean> getBeansById(SugarSession session, String moduleName, List<String> uuids) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest req = new GetEntriesRequest(sessionId, moduleName, uuids);
        SugarPostParameters params = new SugarPostParameters().method("get_entries").restData(req);
        GetEntryResponse entryResp = json.fromJson(postToSugar(restEndpoint, params), GetEntryResponse.class);
        if (entryResp.getEntryList().length > 0) {
            List<SugarBean> beans = new ArrayList<SugarBean>();
            beans.addAll(Arrays.asList(entryResp.getEntryList()));
            return beans;
        } else {
            return null;
        }
    }


    public List<SugarBean> getBeans(SugarSession session, String moduleName, String query) throws SugarApiException {
        String sessionId = session.getSessionID();
        GetEntryListRequest req = new GetEntryListRequest(sessionId, moduleName);
        req.setQuery(query);
        SugarPostParameters params = new SugarPostParameters().method("get_entry_list").restData(req);
        GetEntryResponse entryResp = json.fromJson(postToSugar(restEndpoint, params), GetEntryResponse.class);
        if (entryResp.getEntryList().length > 0) {
            LOG.debug("Found beans");
            List<SugarBean> beans = new ArrayList<SugarBean>();
            beans.addAll(Arrays.asList(entryResp.getEntryList()));
            return beans;
        } else {
            return null;
        }
    }

    public List<SugarBean> getBeans(GetEntryListRequest request) throws SugarApiException {
        SugarPostParameters params = new SugarPostParameters().method("get_entry_list").restData(request);
        GetEntryResponse entryResp = json.fromJson(postToSugar(restEndpoint, params), GetEntryResponse.class);
        if (entryResp.getEntryList().length > 0) {
            List<SugarBean> beans = new ArrayList<SugarBean>();
            beans.addAll(Arrays.asList(entryResp.getEntryList()));
            return beans;
        } else {
            return null;
        }
    }

    public List<SugarBean> getRelationships(SugarSession session, String moduleName, String id, String target, List<String> targetFields) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest req = new GetRelationshipsRequest(sessionId, moduleName, id, target, targetFields);
        SugarPostParameters params = new SugarPostParameters().method("get_relationships").restData(req);
        GetEntryResponse entryResp = json.fromJson(postToSugar(restEndpoint, params), GetEntryResponse.class);
        if (entryResp.getEntryList().length > 0) {
            List<SugarBean> beans = new ArrayList<SugarBean>();
            beans.addAll(Arrays.asList(entryResp.getEntryList()));
            return beans;
        } else {
            return null;
        }
    }

    public SetRelationshipResponse setRelationship(SugarSession session, String moduleName, String module_id, String link_field_name, List<String> related_ids, List<String> name_value_list, Integer delete) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest request = new SetRelationshipRequest(sessionId, moduleName, module_id, link_field_name, related_ids, name_value_list, delete);
        SugarPostParameters params = new SugarPostParameters().method("set_relationship").restData(request);
        return json.fromJson(postToSugar(restEndpoint, params), SetRelationshipResponse.class);
    }

    public SetEntryResponse setEntry(SugarSession session, String moduleName, List<Map<String, Object>> nameValueList) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest request = new SetEntryRequest(sessionId, moduleName, nameValueList);
        SugarPostParameters params = new SugarPostParameters().method("set_entry").restData(request);
        return json.fromJson(postToSugar(restEndpoint, params), SetEntryResponse.class);
    }

    public SetEntriesResponse setEntries(SugarSession session, String moduleName, List<List<Map<String, Object>>> nameValueLists) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest request = new SetEntriesRequest(sessionId, moduleName, nameValueLists);
        SugarPostParameters params = new SugarPostParameters().method("set_entries").restData(request);
        return json.fromJson(postToSugar(restEndpoint, params), SetEntriesResponse.class);
    }

    public DocumentRevision getDocumentRevision(SugarSession session, String revisionId) throws SugarApiException {
        String sessionId = session.getSessionID();
        SugarRequest request = new GetDocumentRevisionRequest(sessionId, revisionId);
        SugarPostParameters params = new SugarPostParameters().method("get_document_revision").restData(request);
        GetDocumentRevisionResponse resp = json.fromJson(postToSugar(restEndpoint, params), GetDocumentRevisionResponse.class);
        return resp.getDocument();
    }


    private class SugarResponseValidator {
        private String response;

        public SugarResponseValidator(String response) {
            this.response = response;
        }

        public ErrorResponse getError() {

            try {
                ErrorResponse resp = json.fromJson(response, ErrorResponse.class);
                if (resp != null && resp.getNumber() != null) {
                    LOG.debug("Sugar API error found");
                    LOG.debug("Error content: " + resp);
                    return resp;
                }
            } catch (JsonSyntaxException e) {
                LOG.debug("No error found");
            }
            return null;
        }
    }

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return DatatypeConverter.parseBase64Binary(json.getAsString());
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(DatatypeConverter.printBase64Binary(src));
        }
    }
}
