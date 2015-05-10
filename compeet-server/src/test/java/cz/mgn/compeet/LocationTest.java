/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.compeet;

import cz.mgn.compeet.model.UserList;
import cz.mgn.compeet.model.UserRegistration;
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
 * Test location-related functions
 * @author aubpe01
 */
public class LocationTest {
    private HttpServer server;
    private WebTarget target;
//    private Weld weld;
   
    public LocationTest() {
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
        
        TestServerUtils.createUser(target, new UserRegistration("Petr A.", "aubi2", "aubrecht@asoftware.cz", "xxx"));
    }
    
    @After
    public void tearDown() {
        if(server!=null) {
            server.shutdownNow();
        }
//        weld.shutdown();
    }


}
