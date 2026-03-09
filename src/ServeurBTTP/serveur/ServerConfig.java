package ServeurBTTP.serveur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class ServerConfig {

    private static HashMap<String, String> configMap = new HashMap<>();
    private static String PATHFILECONFIG;

    static {
        PATHFILECONFIG = "config.properties";
        createConfigFileIfNotExist();
    }

    private static void createConfigFileIfNotExist()  {
        File f = new File(PATHFILECONFIG);
        try {
            if (f.createNewFile()) {
                writeConfig(f);
            }
            return;
        }catch (IOException e) {
            System.out.println("erreur lors de la creation du config.json :" + e);
        }
    }

    private static void writeConfig(File f) throws IOException
    {
        FileOutputStream output = new FileOutputStream(f);
        Properties prop = new Properties();
        configMap.put("server.timeout", "10000");
        prop.putAll(configMap);
        prop.store(output, "fichier de configuration du ServeurBTTP.serveur");
        output.close();
        return;
    }
}
