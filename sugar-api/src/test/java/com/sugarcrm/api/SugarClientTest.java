package com.sugarcrm.api;

import com.sugarcrm.api.v4.impl.DocumentRevision;
import com.sugarcrm.api.v4.impl.SetEntriesResponse;
import com.sugarcrm.api.v4.impl.SetEntryResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Unit tests for Sugar Client
 */
public class SugarClientTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SugarClientTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(SugarClientTest.class);
    }

    /**
     * Rigorous Test :-)
     */
    public void testLogin() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        try {
            SugarSession session = client.getSugarSession("seb", "Seb");
            assertNotNull(session);
            assertNotNull(session.getSessionID());
            User user = session.getUser();
            assertNotNull(user);
            assertEquals(user.getUserName(), "seb");
            assertEquals(user.getUserId(), "4aaf0646-bf1a-89e2-2309-57922f153d8f");
            assertEquals(user.getModuleName(), "Users");
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testGetBean() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            SugarBean bean = client.getBean(session, "Calls", "7975673a-e253-1236-6f2c-5796498bb49c");
            assertEquals("Calls", bean.getModuleName());
            assertEquals("dsadsadsadsadasdasd", bean.get("description"));

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testGetBeansById() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        String[] params = {"7975673a-e253-1236-6f2c-5796498bb49c"};
        List<String> uuids = Arrays.asList(params);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            List<SugarBean> beans = client.getBeansById(session, "Calls", uuids);
            assertNotNull(beans);
            assertEquals(1, beans.size());
            assertEquals("Calls", beans.get(0).getModuleName());
            assertEquals("dsadsadsadsadasdasd", beans.get(0).get("description"));

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testGetBeans() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            List<SugarBean> beans = client.getBeans(session, "Calls", null);
            assertNotNull(beans);
            assertEquals(true, beans.size() > 0);
            assertEquals("Calls", beans.get(0).getModuleName());
            assertNotNull(beans.get(0).getId());

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testSetEntry() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        List<Map<String, Object>> entryData = new ArrayList<Map<String, Object>>();
        Map<String, Object> name = new HashMap<String, Object>();
        name.put("name", "name");
        name.put("value", "API Call");
        entryData.add(name);
        Map<String, Object> date = new HashMap<String, Object>();
        date.put("name", "date_start");
        date.put("value", "2016-07-25 17:30:00");
        entryData.add(date);
        Map<String, Object> date_end = new HashMap<String, Object>();
        date_end.put("name", "date_end");
        date_end.put("value", "2016-07-25 17:45:00");
        entryData.add(date_end);
        Map<String, Object> contact = new HashMap<String, Object>();
        contact.put("name", "contact_id");
        contact.put("value", "7870e090-32cb-4f54-a2c9-57960000121a");
        entryData.add(contact);

        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            SetEntryResponse resp = client.setEntry(session, "Calls", entryData);
            assertNotNull(resp);
            assertNotNull(resp.getId());
            assertEquals(false, resp.getId().isEmpty());
            assertNotNull(resp.getEntry());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }


    public void testSetEntries() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        List<List<Map<String, Object>>> entriesData = new ArrayList<List<Map<String, Object>>>();
        List<Map<String, Object>> entryData = new ArrayList<Map<String, Object>>();
        Map<String, Object> name = new HashMap<String, Object>();
        name.put("name", "name");
        name.put("value", "API Call");
        entryData.add(name);
        Map<String, Object> date = new HashMap<String, Object>();
        date.put("name", "date_start");
        date.put("value", "2016-07-25 17:30:00");
        entryData.add(date);
        Map<String, Object> date_end = new HashMap<String, Object>();
        date_end.put("name", "date_end");
        date_end.put("value", "2016-07-25 17:45:00");
        entryData.add(date_end);
        Map<String, Object> contact = new HashMap<String, Object>();
        contact.put("name", "contact_id");
        contact.put("value", "7870e090-32cb-4f54-a2c9-57960000121a");
        entryData.add(contact);
        entriesData.add(entryData);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            SetEntriesResponse resp = client.setEntries(session, "Calls", entriesData);
            assertNotNull(resp);
            assertNotNull(resp.getIds());
            assertEquals(true, resp.getIds().size() > 0);
            assertNotNull(resp.getIds().get(0));
            assertEquals(false, resp.getIds().get(0).isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testGetRelationships() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        String[] params = {"id", "names"};
        List<String> fields = Arrays.asList(params);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            List<SugarBean> beans = client.getRelationships(session, "Contacts", "7870e090-32cb-4f54-a2c9-57960000121a", "calls", fields);
            assertNotNull(beans);
            assertEquals(3, beans.size());
            assertEquals("Calls", beans.get(0).getModuleName());
            assertEquals("7975673a-e253-1236-6f2c-5796498bb49c", beans.get(0).get("id"));
            assertEquals("Calls", beans.get(1).getModuleName());
            assertEquals("cfb22060-6336-d7d6-5a57-579649f0bcc3", beans.get(1).get("id"));

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    public void testGetDocumentRevision() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            DocumentRevision doc = client.getDocumentRevision(session,"5de40f18-b1eb-07ce-ef5e-579657237720");
            assertNotNull(doc);
            assertEquals("Calendrier-de-place-2016.pdf", doc.getFilename());
            assertNotNull(doc.getContent());
            assertEquals("5de40f18-b1eb-07ce-ef5e-579657237720",doc.getId());

        } catch (Exception e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

}
