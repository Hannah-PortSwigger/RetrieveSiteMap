# Retrieve Site Map
For Burp Suite Enterprise Edition

Please note that extensions are written by third party users of Burp, and PortSwigger makes no warranty about their quality or usefulness for any particular purpose.

---
This extension will generate a TXT file immediately after a scan has finished, containing a list of all URLs found in the Site Map. The file will be stored on the Scanning Machine that performed the scan.

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
5. Retrieve your file from your Scanning Machine - it will be located according to the `folderPath` that is set. If in doubt, the file location and name will be output in the Scan log.

## Troubleshooting
If the report has not been generated, check your scan debug log for any exceptions. For details on how to find this, refer to [the documentation](https://portswigger.net/burp/documentation/enterprise/user-guide/troubleshooting).

If you have received an `AccessDeniedException`, then make sure that you have write permission to the folder you have configured.

### Using Gradle
- If you do not have Gradle already installed, follow the installation instructions [here](https://gradle.org/install/).
- Once Gradle is installed, run `./gradlew build` from the extension directory using the command line.
- Make sure you are using the latest version of Gradle.

If no changes to the code are required, a prebuilt JAR file is available under `build/libs/RetrieveSiteMap-2.0.jar`. It is preferable to compile your own JAR file.

## Changelog

### v2.0 Update
- Rewritten in Montoya API
- Improved file writing mechanism

### v1.0 Initial release
- Contains initial functionality
- Written using the legacy Extender API