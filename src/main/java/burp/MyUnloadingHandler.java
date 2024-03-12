package burp;

import burp.api.montoya.extension.ExtensionUnloadingHandler;
import burp.api.montoya.sitemap.SiteMap;

public class MyUnloadingHandler implements ExtensionUnloadingHandler
{
    private final SiteMap siteMap;
    private final FileSaver fileSaver;

    public MyUnloadingHandler(SiteMap siteMap, FileSaver fileSaver)
    {
        this.siteMap = siteMap;
        this.fileSaver = fileSaver;
    }

    @Override
    public void extensionUnloaded()
    {
        fileSaver.writeToFile(siteMap.requestResponses());
    }
}
