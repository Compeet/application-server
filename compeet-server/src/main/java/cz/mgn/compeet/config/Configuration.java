/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.compeet.config;

import cz.mgn.compeet.service.DbAccessService;

/**
 *
 * @author aubpe01
 */
public class Configuration {
    private static DbAccessService dbAccess = new DbAccessService();
    
    public static DbAccessService getService() {
        return dbAccess;
    }
    
}
