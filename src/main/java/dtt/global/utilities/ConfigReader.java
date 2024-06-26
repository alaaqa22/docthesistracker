package dtt.global.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;
import java.util.Properties;



/**
 * Utility class for reading configuration properties from a properties file.
 * @author Johannes Silvennoinen
 */
public final class ConfigReader {

    private ConfigReader() {}

    private static final Logger LOGGER = LogManager.getLogger(ConfigReader.class);
    private static final String PROPERTIES_FILE_PATH = "/config/configuration.properties";
    private static Properties properties;

    public static final String PAGINATION_MAX_ITEMS = "PAGINATION_MAX_ITEMS";
    public static final String ROOT_ADMIN = "ROOT_ADMIN";
    public static final String COLOR_SCHEME = "COLOR_SCHEME";
    public static final String IMPRINT = "IMPRINT";
    public static final String LOGO_PATH = "LOGO_PATH";
    public static final String IMPRINT_FILE_PATH = "/config/impressumContent.txt";

    public static final String PRODUCTION_MODE = "PRODUCTION_MODE";
    public static final String EMAIL_PATTERN = "EMAIL_PATTERN";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String DATABASE_USER = "DATABASE_USER";
    public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    public static final String DATABASE_DRIVER = "DATABASE_DRIVER";
    public static final String DATABASE_SIZE = "DATABASE_SIZE";
    public static final String SSL = "SSL";
    public static final String SSL_FACTORY = "SSL_FACTORY";
    public static final String PASSWORD_PATTERN = "PASSWORD_PATTERN";

    /**
     * Reads the configuration properties from the properties file and assigns them
     * to a static Properties object.
     */
    public static void loadProperties() {
        try (InputStream inputStream = ConfigReader.class.getResourceAsStream(PROPERTIES_FILE_PATH)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.fatal("Could not read configuration.properties, path is " + PROPERTIES_FILE_PATH);
            throw new RuntimeException(e);
        }
    }
    /**
     * Retrieves the value of the specified property key.
     * The configuration properties must be loaded using {@link #loadProperties()} before calling this method.
     *
     * @param key The property key.
     * @return The value associated with the key, or null if the key is not found.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Checks if properties are loaded.
     *
     * @return true if properties are loaded, i.e., the properties object is not null,
     *         false otherwise.
     */
    public static boolean arePropertiesLoaded() {
        return properties != null;
    }

    /**
     * Reads and returns the contents of the Impressum file (impressum.txt) as a string.
     * The file is expected to be found at the path defined by IMPRINT_FILE_PATH.
     * If an IOException occurs during reading of the file, it logs a fatal error.
     *
     * @return The contents of the impressum file as a String, or null if the file does not exist, is empty or an IOException occurs.
     */
    public static String getImpressumContent(){
        String impressumContent = null;
        try (InputStream inputStream = ConfigReader.class.getResourceAsStream(IMPRINT_FILE_PATH)) {
            if (inputStream != null) {
                impressumContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            LOGGER.fatal("Failed to read impressum.txt file.", e);
        }
        return impressumContent;
    }
}

