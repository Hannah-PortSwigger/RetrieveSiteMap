# Retrieve Site Map
For Burp Suite Enterprise Edition

Please note that extensions are written by third party users of Burp, and PortSwigger makes no warranty about their quality or usefulness for any particular purpose.

---
This extension will raise an informational scan issue of certain confidence containing a list of all URLs found in the Site Map. This is done at the beginning of the Audit phase.

## Usage
1. Build the extension using `./gradlew build`. Alternatively, download the extension from the "Releases" section.
2. [Load the extension into Burp Enterprise](https://portswigger.net/burp/documentation/enterprise/user-guide/extensions/adding-extensions#adding-custom-extensions-to-burp-suite-enterprise-edition), and [apply the extension to your site](https://portswigger.net/burp/documentation/enterprise/user-guide/working-with-sites/site-settings/scanning-with-extensions).
3. Run a scan as normal.
4. Check your information-level issues.

### Using Gradle
- If you do not have Gradle already installed, follow the installation instructions [here](https://gradle.org/install/).
- Once Gradle is installed, run `./gradlew build` from the extension directory using the command line.
- Make sure you are using the latest version of Gradle.

If no changes to the code are required, a prebuilt JAR file is available under Releases. It is preferable to compile your own JAR file.

## Changelog

### v2.3 Update
- Adjust to use a scan check instead of writing a file.

### v2.2 Update
- Fix format of filename to prevent issues on Windows machines.

### v2.1 Update
- Write the site map to the file system at specific intervals throughout the Scan.

### v2.0 Update
- Rewritten in Montoya API.
- Improved file writing mechanism.

### v1.0 Initial release
- Contains initial functionality.
- Written using the legacy Extender API.