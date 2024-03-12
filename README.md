# Retrieve Site Map
For Burp Suite Enterprise Edition

Please note that extensions are written by third party users of Burp, and PortSwigger makes no warranty about their quality or usefulness for any particular purpose.

---
This extension will generate a TXT file immediately after a scan has finished, containing a list of all URLs found in the Site Map. The file will be stored on the Scanning Machine that performed the scan.
Throughout the scan, this extension will save the site map to the TXT file every ten minutes.

The filename of the scan will be the start time of the scan, in the format `site-map-<TIME_STAMP>.txt`.

## Limitations
- File will be stored on Scanning Machine.
- Previous files will not be removed from the folder, so ensure that you are regularly cleaning up old files.
- This requires a minimum Scanner version of 2023.1.
- This extension assumes you are using a Standard install. This has not been tested on a Cloud deployment.

## Usage
1. Download this repository, and check the `folderPath` variable in `Extension.java` is pointed to a location where you have write permission **on the Scanning Machine**.
2. Build the extension using `./gradlew build`.
3. [Load the extension into Burp Enterprise](https://portswigger.net/burp/documentation/enterprise/user-guide/extensions/adding-extensions#adding-custom-extensions-to-burp-suite-enterprise-edition), and [apply the extension to your site](https://portswigger.net/burp/documentation/enterprise/user-guide/working-with-sites/site-settings/scanning-with-extensions).
4. Run a scan as normal.
5. Retrieve your file from your Scanning Machine - it will be located according to the `folderPath` that is set. If in doubt, the file location and name will be output in the Scan log. This information will also be present in the Scan event log, if a backup has run.

### Additional configuration
You may wish to adjust the time period for when the site map data is saved. You can do this by adjusting the initial delay and delay in `Extension.java`.

- To adjust the initial delay, edit line 34 in `Extension.java`.
- To adjust the subsequent delay, edit line 35 in `Extension.java`.
- To adjust the units used for the delay period, edit line 36 in `Extension.java`.

After adjusting the configuration, rebuild the extension as detailed in step 2.

## Troubleshooting
If the report has not been generated, check your scan debug log for any exceptions. For details on how to find this, refer to [the documentation](https://portswigger.net/burp/documentation/enterprise/user-guide/troubleshooting).

If you have received an `AccessDeniedException`, then make sure that you have write permission to the folder you have configured.

### Using Gradle
- If you do not have Gradle already installed, follow the installation instructions [here](https://gradle.org/install/).
- Once Gradle is installed, run `./gradlew build` from the extension directory using the command line.
- Make sure you are using the latest version of Gradle.

If no changes to the code are required, a prebuilt JAR file is available under Releases. It is preferable to compile your own JAR file.

## Changelog

### v2.1 Update
- Write the site map to the file system at specific intervals throughout the Scan.

### v2.0 Update
- Rewritten in Montoya API
- Improved file writing mechanism

### v1.0 Initial release
- Contains initial functionality
- Written using the legacy Extender API