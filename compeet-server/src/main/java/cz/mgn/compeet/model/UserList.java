/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.compeet.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubpe01
 */
public class UserList {
    private List<User> users = new ArrayList<>();

    public UserList() {
    }
    
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
    
}
