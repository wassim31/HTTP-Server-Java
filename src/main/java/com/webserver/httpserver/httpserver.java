package com.webserver.httpserver;

import com.webserver.httpserver.config.Configuration;
import com.webserver.httpserver.config.ConfigurationManager;

public class httpserver {
    public static void main(String[] args) {
        System.out.println("Server running");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using port : " + conf.getPort());
        System.out.println("Using webroot : " + conf.getWebroot());
    }
}
