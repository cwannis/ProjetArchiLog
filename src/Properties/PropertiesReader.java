package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private final String path = "src/Properties/config.properties";
    private final Properties properties;
    private static PropertiesReader proprieties;

    static
    {
        proprieties = new PropertiesReader();
    }

    public PropertiesReader()  {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.err.println("Error loading properties file");
            throw new RuntimeException(e);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public static PropertiesReader getInstance() {
        return proprieties;
    }

}
