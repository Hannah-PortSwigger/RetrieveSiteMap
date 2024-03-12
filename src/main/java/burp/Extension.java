package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

import java.nio.file.Path;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

        Path filePath = Path.of(folderPath + "site-map-" + extensionLoadedInstant.toString() + ".txt");
        FileSaver fileSaver = new FileSaver(logger, filePath);

        montoyaApi.extension().registerUnloadingHandler(new MyUnloadingHandler(montoyaApi.siteMap(), fileSaver));

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> fileSaver.writeToFile(montoyaApi.siteMap().requestResponses()),
                10,
                10,
                TimeUnit.MINUTES
        );

        logger.logOutput("Loaded");
    }
}
