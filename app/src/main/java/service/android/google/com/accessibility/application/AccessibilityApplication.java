package service.android.google.com.accessibility.application;

import android.app.Application;
import android.content.Context;
import android.support.graphics.drawable.BuildConfig;

import service.android.google.com.accessibility.dagger.module.ApplicationModule;
import timber.log.Timber;

/**
 * Created by tim on 05.03.16.
 */
public class AccessibilityApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }

//        applicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this))
//                .build();
    }

    public static AccessibilityApplication get(Context context){
        return (AccessibilityApplication) context.getApplicationContext();
    }

//    public ApplicationComponent getComponent() {
//        return applicationComponent;
//    }
}
