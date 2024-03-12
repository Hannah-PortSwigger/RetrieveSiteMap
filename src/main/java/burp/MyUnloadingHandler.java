package burp;

import burp.api.montoya.extension.ExtensionUnloadingHandler;
import burp.api.montoya.sitemap.SiteMap;

import java.util.concurrent.ScheduledExecutorService;

public class MyUnloadingHandler implements ExtensionUnloadingHandler
{
    private final SiteMap siteMap;
    private final FileSaver fileSaver;
    private final ScheduledExecutorService scheduledExecutorService;

    public MyUnloadingHandler(SiteMap siteMap, FileSaver fileSaver, ScheduledExecutorService scheduledExecutorService)
    {
        this.siteMap = siteMap;
        this.fileSaver = fileSaver;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public void extensionUnloaded()
    {
        scheduledExecutorService.shutdown();

        fileSaver.writeToFile(siteMap.requestResponses());
    }
}
