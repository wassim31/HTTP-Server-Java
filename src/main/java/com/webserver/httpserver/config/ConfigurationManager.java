package com.webserver.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.webserver.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;
    public ConfigurationManager() {
    }
    public static ConfigurationManager getInstance()
    {
        if(myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }
    /*
        the next method is used to load a configuration file
     */
    public void loadConfigurationFile(String filePath)  {
        FileReader myFileReader = null;
        try {
            myFileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer myStringBuffer = new StringBuffer();
        int i;
        try {
            while ( (i = myFileReader.read()) != -1)
            {
                myStringBuffer.append((char)i);
                
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(myStringBuffer.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the configuration File");

        }
        try {
            myCurrentConfiguration = Json.fromJson(conf,Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the configuration File, internal");
        }
    }

    public Configuration getCurrentConfiguration() {
        if (myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No current Configuration set");
        }
        return myCurrentConfiguration;
    }
}
