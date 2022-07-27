package burp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BurpExtender implements IBurpExtender, IExtensionStateListener
{
    private String folderPath = "/var/log/BurpSuiteEnterpriseEdition/";     // CHANGE ME!
    private String fileName;

    private static String NAME = "Retrieve Site Map contents extension";

    private IBurpExtenderCallbacks callbacks;
    private IExtensionHelpers helpers;
    private PrintWriter stdout, stderr;
    private LocalDateTime extensionLoadedTime;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        extensionLoadedTime = LocalDateTime.now();

        this.callbacks = callbacks;
        this.helpers = callbacks.getHelpers();

        stdout = new PrintWriter(callbacks.getStdout(), true);
        stderr = new PrintWriter(callbacks.getStderr(), true);

        callbacks.registerExtensionStateListener(this);
        callbacks.setExtensionName(NAME);

        stdout.println( NAME + " - Loaded");
    }

    @Override
    public void extensionUnloaded() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        fileName = "site-map-" + dtf.format(extensionLoadedTime) + ".txt";

        IHttpRequestResponse[] reqRespArr = callbacks.getSiteMap(null);

        List<String> urlList = new ArrayList<>();

        for (IHttpRequestResponse reqResp : reqRespArr)
        {
            urlList.add(helpers.analyzeRequest(reqResp).getUrl().toString());
        }

        FileWriter writer = null;
        try
        {
            writer = new FileWriter(folderPath + fileName);
        }
        catch (IOException e)
        {
            stderr.println(NAME + " - File creation failed:\r\n" + e);
        }

        for (String url : urlList)
        {
            try
            {
                writer.write(url + "\r\n");
            }
            catch (IOException e)
            {
                stderr.println(NAME + " - Writing to file failed:\r\n" + e);
            }
        }

        try
        {
            writer.close();
        }
        catch (IOException e)
        {
            stderr.println(NAME + " - Closing file failed:\r\n" + e);
        }

        stdout.println((NAME + " - Site map saved at " + folderPath + fileName));
    }
}
