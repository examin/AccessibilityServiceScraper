package service.android.google.com.accessibility.dagger.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import service.android.google.com.accessibility.dagger.module.ApplicationModule;

/**
 * Created by tim on 06.03.16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application application();
}