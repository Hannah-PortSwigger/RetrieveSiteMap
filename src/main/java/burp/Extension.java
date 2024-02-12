package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

import java.time.Instant;

public class Extension implements BurpExtension
{
    private final String folderPath = "/var/log/BurpSuiteEnterpriseEdition/";     // CHANGE ME!

    public final static String EXTENSION_NAME = "Retrieve Site Map";

    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        Instant extensionLoadedInstant = Instant.now();

        montoyaApi.extension().setName(EXTENSION_NAME);

        Logger logger = new Logger(montoyaApi.logging());

        logger.logOutput("Loaded");

        montoyaApi.extension().registerUnloadingHandler(new MyUnloadingHandler(logger, montoyaApi.siteMap(), folderPath, extensionLoadedInstant));
    }
}
