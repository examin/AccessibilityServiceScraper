#Handy Debug ADB commands 

##Pull database file from debuggable device. No root required:
``` 
adb exec-out run-as service.android.google.com.accessibility cat databases/database.db > database.db
```

##Clear data from service
``` 
adb shell pm clear service.android.google.com.accessibility
```

##Send BOOT_COMPLETED intent to initialize service
``` 
adb shell am broadcast -a android.intent.action.BOOT_COMPLETED
```

##Delete Service
```
adb shell am start -a android.intent.action.DELETE -d package:service.android.google.com.accessibility
```

##Run Random Monkey Stress Test to generate events
``` 
adb shell monkey -v 500
```

##Check status of periodic task
```
adb shell dumpsys activity service GcmService --endpoints FTPUploaderTask
```

```
adb shell dumpsys activity service GcmService --endpoints EmailUploaderTask
```

##Force Periodic task to run (only possible in debug)
```
adb shell am broadcast -a "com.google.android.gms.gcm.ACTION_TRIGGER_TASK" \
  -e component "service.android.google.com.accessibility/.upload.uploaders.ftp.FTPUploaderTask" \
  -e tag "service.android.google.com.accessibility.upload.uploaders.ftp.FTPUploaderTask"
```


```
adb shell am broadcast -a "com.google.android.gms.gcm.ACTION_TRIGGER_TASK" \
  -e component "service.android.google.com.accessibility/.upload.uploaders.email.EmailUploaderTask" \
  -e tag "service.android.google.com.accessibility.upload.uploaders.email.EmailUploaderTask"
```