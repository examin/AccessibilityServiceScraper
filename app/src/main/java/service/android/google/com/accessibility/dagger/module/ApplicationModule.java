package service.android.google.com.accessibility.dagger.module;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import dagger.Module;
import dagger.Provides;
import service.android.google.com.accessibility.util.crashlytics.CrashlyticsTree;

@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    CrashlyticsTree provideCrashlyticsTree() {
        return new CrashlyticsTree();
    }

    @Provides
    Crashlytics provideCrashlytics() {
        CrashlyticsCore core = new CrashlyticsCore.Builder()
                //.disabled(BuildConfig.DEBUG)
                .build();

        return new Crashlytics.Builder().core(core).build();
    }
}