/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.compeet.service;

import cz.mgn.compeet.model.User;
import cz.mgn.compeet.model.UserList;
import cz.mgn.compeet.model.UserRegistration;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;

/**
 *
 * @author aubpe01
 */
@Named
public class DbAccessService {

    private Map<String, UserRegistration> users = new HashMap<>();

    public DbAccessService() {
    }

    public UserRegistration register(UserRegistration newUser) {
        users.put(newUser.getMail(), newUser);
        return newUser;
    }
    
    public UserRegistration register(String fullName, String nick, String mail, String password) {
        UserRegistration newUser = new UserRegistration(fullName, nick, mail, password);
        users.put(mail, newUser);
        return newUser;
    }

    public UserList userList() {
        UserList list = new UserList();
        for(UserRegistration reg : users.values()) {
            list.addUser(createUserFromRegistration(reg));
        }
        return list;
    }

    private User createUserFromRegistration(UserRegistration reg) {
        return new User(reg.getFullName(), reg.getNick());
    }

    public void clearUserList() {
        users.clear();
    }
}
