package ru.java.mentor.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader propertyReader;

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

    public String read(String key) throws IOException {
        Properties properties = new Properties();
        String property;
        FileInputStream in = new FileInputStream("D:\\JavaMentors\\project1\\src\\main\\resources\\db.properties");
        properties.load(in);
        property = properties.getProperty(key);
        return property;
    }
}
