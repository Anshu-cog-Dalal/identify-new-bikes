package org.example.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        properties = new Properties();
        // Path relative to project root (where Maven runs from)
        String configPath = "src/test/resources/config/config.properties";
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("[ConfigReader] Failed to load config.properties at: "
                    + configPath + "\n" + e.getMessage());
        }
    }


    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("[ConfigReader] Key not found in config.properties: " + key);
        }
        return value.trim();
    }


    public static String get(String key, String defaultValue) {
        String value = properties.getProperty(key);
        return (value == null || value.trim().isEmpty()) ? defaultValue : value.trim();
    }


    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }


    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}