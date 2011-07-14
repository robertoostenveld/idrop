/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.irods.jargon.idrop.desktop.systraygui.services;

import java.util.Properties;
import org.irods.jargon.core.connection.IRODSAccount;

import org.irods.jargon.idrop.exceptions.IdropException;

/**
 * 
 * @author mikeconway
 */
public interface IdropConfigurationService {

    public static final String IDROP_PROPS_FILE_NAME = "idrop.properties";
    public static final String FORCE_MODE = "force.mode";
    public static final String FORCE_NO_SYNCH = "force.no.synch";
    public static final String LOGIN_PRESET = "login.preset";
    public static final String SHOW_STARTUP = "show.startup";
    public static final String SHOW_GUI = "idrop.show.gui";
    public static final String DEVICE_NAME = "idrop.device.name";
    // account info for cache
    public static final String ACCOUNT_CACHE_HOST = "irods.account.host";
    public static final String ACCOUNT_CACHE_PORT = "irods.account.port";
    public static final String ACCOUNT_CACHE_ZONE = "irods.account.zone";
    public static final String ACCOUNT_CACHE_RESOURCE = "irods.account.resource";
    public static final String ACCOUNT_CACHE_USER_NAME = "irods.account.user";
    public static final String ACCOUNT_CACHE_ROOT_DIR = "irods.account.root.dir";
    public static final String POLICY_AWARE_PROPERTY = "policy.aware";

    Properties bootstrapConfiguration() throws IdropException;

    /**
     * Save the database configuration information to a properties file
     * 
     * @throws IdropException
     */
    void saveConfigurationToPropertiesFile() throws IdropException;

    /**
     * Update the configuration database, and update the cached configs, with the given information
     * @param key
     * @param value
     * @throws IdropException 
     */
    void updateConfig(final String key, final String value) throws IdropException;

    /**
     * Save a given iRODS account as a cached last login in the configuration database
     * @param irodsAccount {@link IRODSAccount} to be cached
     * @throws IdropException 
     */
    void saveLogin(final IRODSAccount irodsAccount) throws IdropException;

    void removeConfigProperty(final String key) throws IdropException;
}
