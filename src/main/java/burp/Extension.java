package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class Extension implements BurpExtension
{
    public final static String EXTENSION_NAME = "Retrieve Site Map";

    @Override
    public void initialize(MontoyaApi montoyaApi)
    {
        montoyaApi.extension().setName(EXTENSION_NAME);

        montoyaApi.scanner().registerScanCheck(new ExtensionScanCheck(montoyaApi.siteMap()));
    }
}
