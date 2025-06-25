package singleton;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class Configuration {
    private static Configuration instance = null;
//    static {
//        instance = null;
//    }
    private String webDomain;
    private int webPort;

    public static Configuration getInstance() {
        if (instance == null) {
            String domain = "uim.fei.stuba.sk";
            int port = 443;
            try {
                Properties prop = new Properties();
                prop.load(Configuration.class.getResourceAsStream("/config.properties"));
                domain = prop.getProperty("web.domain", "uim.fei.stuba.sk");
                port = stringToInt(prop.getProperty("web.port"), 443);
            }
            catch (IOException exception) {
            }
            instance = new Configuration(domain, port);
        }
        return instance;
    }

    private static int stringToInt(String number, int defaultValue) {
        try {
            return Integer.parseInt(number);
        }
        catch (NumberFormatException exception) {
            return defaultValue;
        }
    }

    private Configuration(String webDomain, int webPort) {
        this.webDomain = webDomain;
        this.webPort = webPort;
    }

    public String getWebDomain() {
        return webDomain;
    }

    public void setWebDomain(String webDomain) {
        this.webDomain = webDomain;
    }

    public int getWebPort() {
        return webPort;
    }

    public Language getLanguage() {
        Map<String, Language> languageMap = Map.of(
            "en", Language.ENGLISH,
            "sk", Language.SLOVAK,
            "es", Language.SPANISH
        );

        String languageName = Locale.getDefault().getLanguage();
        return languageMap.getOrDefault(languageName, Language.ENGLISH);
    }
}
