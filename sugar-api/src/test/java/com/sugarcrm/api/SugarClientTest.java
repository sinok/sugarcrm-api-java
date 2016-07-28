package com.sugarcrm.api;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.List;

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

    public void testGetBeans() {
        SugarClient client = new SugarClient("http://test.itanis.fr/suite", SugarClient.SUGAR_API_VERSION_4_1);
        String[] params = {"7975673a-e253-1236-6f2c-5796498bb49c"};
        List<String> uuids = Arrays.asList(params);
        try {
            SugarSession session = client.getSugarSession(new SugarCredentials("seb", "Seb"));
            List<SugarBean> beans = client.getBeans(session, "Calls", uuids);
            assertNotNull(beans);
            assertEquals(1, beans.size());
            assertEquals("Calls", beans.get(0).getModuleName());
            assertEquals("dsadsadsadsadasdasd", beans.get(0).get("description"));

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


}
