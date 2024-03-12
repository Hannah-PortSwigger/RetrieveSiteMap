package burp;

import burp.api.montoya.http.message.HttpRequestResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSaver
{
    private final Logger logger;
    private final Path filePath;

    public FileSaver(Logger logger, Path filePath)
    {
        this.logger = logger;
        this.filePath = filePath;
    }

    public void writeToFile(List<HttpRequestResponse> httpRequestResponses)
    {
        StringBuilder sb = new StringBuilder();

        for (HttpRequestResponse requestResponse : httpRequestResponses)
        {
            sb.append(requestResponse.url()).append("\r\n");
        }

        try
        {
            Files.writeString(filePath, sb.toString());
            logger.logOutput("Site map saved to " + filePath);
        }
        catch (Exception e)
        {
            logger.logError("Failed to save site map", e);
        }
    }
}
