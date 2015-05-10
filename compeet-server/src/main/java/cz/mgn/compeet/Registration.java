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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author aubpe01
 */
//@ApplicationScoped
@Path("/registration")
public class Registration {
//    @Inject
    DbAccessService service = Configuration.getService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("register")
//    public UserRegistration register(@QueryParam("fullName") String fullName,@QueryParam("nick") String nick,@QueryParam("mail") String mail,@QueryParam("password") String password) {
    public UserRegistration register(UserRegistration reg) {
        return service.register(reg);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("test")
    public String test() {
        return "success!!!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user-list")
    public UserList userList() {
        return service.userList();
    }
    
}
