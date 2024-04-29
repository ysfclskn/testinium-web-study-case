package config;

public class BaseConfig {
    String locale = System.getProperty("locale");
    String browser = System.getProperty("browser");
    String env = System.getProperty("env");
    String headless = System.getProperty("headless");

    public String getBrowser() {
        return browser != null ? browser : "chrome";
    }

    public String getLocale() {
        return locale != null ? locale : "tr";
    }

    public String getUrl() {
        env = (env != null) ? env : "prod";
        String url = null;
        switch (env) {
            case "test" -> url = "";
            case "prod" -> url = "https://www.beymen.com";
        }
        return url;
    }

    public Boolean getHeadless() {
        return (headless != null) ? Boolean.parseBoolean(headless) : false;
    }
}
