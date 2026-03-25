package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApplicationConfig.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input != null) {
                properties.load(input);
                System.out.println("application.properties loaded successfully.");
            } else {
                System.out.println("application.properties NOT found. Using default values.");
            }

        } catch (IOException e) {
            System.out.println("Error loading application.properties. Using defaults.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static int getIntProperty(String key, int defaultValue) {
        return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }

    public static String getRepositoryType() {
        return getProperty("repository.type", "CACHE");
    }

    public static String getDatabaseUrl() {
        return getProperty("db.url", "jdbc:h2:./data/quantitydb");
    }

    public static String getDatabaseUsername() {
        return getProperty("db.username", "sa");
    }

    public static String getDatabasePassword() {
        return getProperty("db.password", "");
    }

    public static String getDatabaseDriver() {
        return getProperty("db.driver", "org.h2.Driver");
    }

    public static int getPoolSize() {
        return getIntProperty("db.pool.size", 5);
    }
}