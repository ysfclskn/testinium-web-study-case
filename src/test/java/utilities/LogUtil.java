package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);


    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logError(String message, Throwable exception) {
        logger.error(message, exception);
    }

    public static void logTrace(String message) {
        logger.trace(message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logWarning(String message) {
        logger.warn(message);
    }
}
