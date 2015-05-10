/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.compeet;

import cz.mgn.compeet.model.UserRegistration;
import java.net.URI;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author aubpe01
 */
public class TestServerUtils {

    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8040/compeet/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
     * application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example package
        final ResourceConfig rc = new ResourceConfig().packages("cz.mgn.compeet");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static UserRegistration createUser(WebTarget target, UserRegistration sampleUser) {
        return target.path("/registration/register")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(sampleUser, MediaType.APPLICATION_JSON_TYPE), UserRegistration.class);
    }

}
