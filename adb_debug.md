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