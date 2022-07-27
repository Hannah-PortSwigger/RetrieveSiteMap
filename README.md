# Retrieve Site Map
For Burp Suite Enterprise Edition

Please note that extensions are written by third party users of Burp, and PortSwigger makes no warranty about their quality or usefulness for any particular purpose.

---
This extension will generate a TXT file immediately after a scan has finished, containing a list of all URLs found in the Site Map. The file will be stored on the Scanning Machine that performed the scan.

The filename of the scan will be the start time of the scan, in the format `site-map-yyyy-MM-dd-HH-mm-ss`.

## Limitations
- File will be stored on Scanning Machine.
- Previous files will not be removed from the folder, so ensure that you are regularly cleaning up old files.
- This extension assumes you are using a Standard install. This has not been tested on a Cloud deployment.

## Usage
1. Download this repository, and check the `folderPath` variable in `BurpExtender.java` is pointed to a location where you have write permission **on the Scanning Machine**.
2. Build the extension using `gradle fatJar`. Please compile using Java 11.
3. [Load the extension into Burp Enterprise](https://portswigger.net/burp/documentation/enterprise/working/scans/extensions), and add the extension to your Site Details page.
4. Run a scan as normal.
5. Retrieve your file from your Scanning Machine - it will be located according to the `folderPath` that is set. If in doubt, the file location and name will be output in the Scan log.

## Troubleshooting
If the report has not been generated, check your scan log for any exceptions - Scan > Reporting & logs > Scan debug log.

If you have received a `FileNotFoundException (Permission denied)`, then make sure that you are writing your report to a location where you have write permission.

### Using Gradle
- If you do not have Gradle already installed, follow the installation instructions [here](https://gradle.org/install/).
- Once Gradle is installed, run `gradle fatJar` from the installation directory using the command line.
- Make sure you are using the latest version of Gradle.
- Please make sure to use Java 11 to compile.

If no changes to the code are required, a prebuilt JAR file is available under `build/libs/`. It is preferable to compile your own JAR file.
