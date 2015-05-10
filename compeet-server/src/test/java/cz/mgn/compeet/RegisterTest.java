/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.compeet;

import cz.mgn.compeet.config.Configuration;
import cz.mgn.compeet.model.UserList;
import cz.mgn.compeet.model.UserRegistration;
import cz.mgn.compeet.service.DbAccessService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test register-related functions
 * @author aubpe01
 */
public class RegisterTest {
    private HttpServer server;
    private WebTarget target;
//    private Weld weld;

    private final DbAccessService dbService = Configuration.getService();

    public RegisterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        weld = new Weld();
//        weld.initialize();

        // start the server
        server = TestServerUtils.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(TestServerUtils.BASE_URI);
    }

    @After
    public void tearDown() {
        if (server != null) {
            server.shutdownNow();
        }
//        weld.shutdown();
    }

    @Test
    public void testTest() {
        String responseMsg = target.path("/registration/test")
                .request().get(String.class);
        assertEquals("success!!!", responseMsg);
    }

    /**
     * Test registration
     */
    @Test
    public void testRegister() {
        UserRegistration sampleUser = new UserRegistration("Petr Aubrecht", "aubi", "aubi@example.com", "abc");
        UserRegistration responseMsg = target.path("/registration/register")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(sampleUser, MediaType.APPLICATION_JSON_TYPE), UserRegistration.class);
        assertEquals(sampleUser, responseMsg);
    }

    /**
     * Test 3 new users and the 1 modification, with the same mail -- not added,
     * just updated.
     */
    @Test
    public void testUserList() {
        dbService.clearUserList();
        UserList responseMsg;
        TestServerUtils.createUser(target, new UserRegistration("a", "a", "a@example.xom", "xxx"));
        responseMsg = target.path("/registration/user-list").request(MediaType.APPLICATION_JSON).get(UserList.class);
        assertEquals(1, responseMsg.getUsers().size());
        TestServerUtils.createUser(target, new UserRegistration("b", "b", "b@example.xom", "xxx"));
        responseMsg = target.path("/registration/user-list").request(MediaType.APPLICATION_JSON).get(UserList.class);
        assertEquals(2, responseMsg.getUsers().size());
        TestServerUtils.createUser(target, new UserRegistration("c", "c", "c@example.xom", "xxx"));
        responseMsg = target.path("/registration/user-list").request(MediaType.APPLICATION_JSON).get(UserList.class);
        assertEquals(3, responseMsg.getUsers().size());

        // created with the same mail, e.g. it is added
        TestServerUtils.createUser(target, new UserRegistration("a1", "a1", "a@example.xom", "xxx"));
        responseMsg = target.path("/registration/user-list").request(MediaType.APPLICATION_JSON).get(UserList.class);
        assertEquals(3, responseMsg.getUsers().size());

    }

}
