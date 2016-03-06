package service.android.google.com.accessibility.dagger.component;

import android.app.Application;

import dagger.Component;
import service.android.google.com.accessibility.dagger.module.ApplicationModule;

@Component(modules = {ApplicationModule.class})
public interface Graph extends BaseGraph {
    final class Initializer{
        private Initializer(){}

        public static Graph init(final Application application){
            return DaggerGraph.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }
    }
}