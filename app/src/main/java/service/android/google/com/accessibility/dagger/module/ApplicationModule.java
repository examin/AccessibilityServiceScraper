package service.android.google.com.accessibility.dagger.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

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
}