package burp;

import burp.api.montoya.extension.ExtensionUnloadingHandler;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.sitemap.SiteMap;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;

public class MyUnloadingHandler implements ExtensionUnloadingHandler
{
    private final Logger logger;
    private final SiteMap siteMap;
    private final String folderPath;
    private final Instant extensionLoadedInstant;

    public MyUnloadingHandler(Logger logger, SiteMap siteMap, String folderPath, Instant extensionLoadedInstant)
    {
        this.logger = logger;
        this.siteMap = siteMap;
        this.folderPath = folderPath;
        this.extensionLoadedInstant = extensionLoadedInstant;
    }

    @Override
    public void extensionUnloaded()
    {
        String outputText = buildOutput(siteMap.requestResponses());

        writeToFile(outputText);
    }

    private String buildOutput(List<HttpRequestResponse> httpRequestResponses)
    {
        StringBuilder sb = new StringBuilder();

        for (HttpRequestResponse requestResponse : httpRequestResponses)
        {
            sb.append(requestResponse.url()).append("\r\n");
        }

        return sb.toString();
    }

    private void writeToFile(String outputText)
    {
        Path filePath = Path.of(folderPath + "site-map-" + extensionLoadedInstant.toString() + ".txt");

        try
        {
            Files.writeString(filePath, outputText);
            logger.logOutput("Site map saved to " + filePath);
        }
        catch (Exception e)
        {
            logger.logError("Failed to save site map", e);
        }
    }
}
