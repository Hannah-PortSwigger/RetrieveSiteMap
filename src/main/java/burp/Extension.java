package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Extension implements BurpExtension
{
    private final String folderPath = "/var/log/BurpSuiteEnterpriseEdition";     // CHANGE ME!

    public final static String EXTENSION_NAME = "Retrieve Site Map";

    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        Instant extensionLoadedInstant = Instant.now();

        montoyaApi.extension().setName(EXTENSION_NAME);
        Logger logger = new Logger(montoyaApi.logging());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm").withZone(ZoneId.systemDefault());

        Path filePath = Path.of(folderPath, "site-map-" + dateTimeFormatter.format(extensionLoadedInstant) + ".txt");
        FileSaver fileSaver = new FileSaver(logger, filePath);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                () -> fileSaver.writeToFile(montoyaApi.siteMap().requestResponses()),
                10,                 // Initial delay
                10,                 // Fixed delay
                TimeUnit.MINUTES    // Time units
        );

        montoyaApi.extension().registerUnloadingHandler(new MyUnloadingHandler(montoyaApi.siteMap(), fileSaver, scheduledExecutorService));

        logger.logOutput("Loaded");
    }
}
