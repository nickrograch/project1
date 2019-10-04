package ru.java.mentor.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader propertyReader;
    private static final String propertiesFile = "db.properties";

    String connection;

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public static PropertyReader getInstacne(){
        if (propertyReader == null){
            propertyReader = new PropertyReader();
        }
        return propertyReader;
    }

    public static String read(String key)  throws ReaderException {
        Properties properties = new Properties();
        String property;
        try {
            InputStream stream = PropertyReader.class.getClassLoader().getResourceAsStream(propertiesFile);
            properties.load(stream);
            property = properties.getProperty(key);
            return property;
        }
        catch (IOException e){
            throw new ReaderException("Properties file not found");
        }
    }
}
