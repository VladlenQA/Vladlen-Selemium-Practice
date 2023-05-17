package org.brit.Selenide.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static PropertiesReader instance = null;

    private Properties properties;

    PropertiesReader() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File("src/main/resources/app.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesReader getInstance() {
        if (instance == null) {
            instance = new PropertiesReader();
        }
        return instance;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
