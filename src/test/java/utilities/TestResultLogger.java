package utilities;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestResultLogger implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        String testName = context.getDisplayName();
        LogUtil.logInfo(testName + " PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        LogUtil.logError(testName + " FAILED => ", cause);
    }
}
