package burp;

import burp.api.montoya.logging.Logging;

import java.io.IOException;

public class Logger
{
    private final Logging logging;

    public Logger(Logging logging)
    {
        this.logging = logging;
    }

    public void logOutput(String message)
    {
        logging.logToOutput(Extension.EXTENSION_NAME + " - " + message);
        logging.raiseInfoEvent(message);
    }

    public void logError(String message, Exception e)
    {
        logging.logToError(Extension.EXTENSION_NAME + " - " + message + ": " + e);
        logging.raiseErrorEvent(message + " - check scan log for further details.");
    }
}
