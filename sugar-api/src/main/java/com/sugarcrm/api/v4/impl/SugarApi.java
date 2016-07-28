package com.sugarcrm.api.v4.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sugarcrm.api.*;
import com.sugarcrm.api.SugarBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        json = new GsonBuilder().create();
    }


    public class NameValue {
        protected String name;
        protected String value;
    }

    public String postToSugar(String urlStr, SugarPostParameters params) throws SugarApiException {
        LOG.debug("JSON rest_data: "+json.toJson(params.restData));
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
            LOG.debug("Response: "+response);
            ErrorResponse err = new SugarResponseValidator(response).getError();
            LOG.info("Error response: " + err);

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

    public List<SugarBean> getBeans(SugarSession session, String moduleName, List<String> uuids) throws SugarApiException {
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


    private class SugarResponseValidator {
        private String response;

        public SugarResponseValidator(String response) {
            this.response = response;
        }

        public ErrorResponse getError() {

            try {
                ErrorResponse resp = json.fromJson(response, ErrorResponse.class);
                LOG.info("Sugar API error found");
                LOG.info("Error content: " + resp);
                if (resp != null && resp.getNumber() != null) {
                    return resp;
                }
            } catch (JsonSyntaxException e) {
                LOG.debug("No error found");
            }
            return null;
        }
    }
}
