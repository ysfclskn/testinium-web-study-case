package config;

public class BaseConfig {
    String locale = System.getenv("local");
    String browser = System.getenv("browser");
    String env = System.getenv("env");
    String headless = System.getenv("headless");

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
