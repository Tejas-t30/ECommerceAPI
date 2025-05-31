package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties properties;
    private String filePath=".\\src\\test\\resources\\config.properties";

    public ConfigReader()
    {
        properties=new Properties();
        try {
            FileInputStream fi=new FileInputStream(filePath);
            properties.load(fi);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties file");
        }

    }
    public String getProperty(String key)
    {
        return properties.getProperty(key);
    }

    public int getIntProperty(String key)
    {
        return Integer.parseInt(properties.getProperty(key));
    }



}
