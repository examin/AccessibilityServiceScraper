package service.android.google.com.accessibility.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import service.android.google.com.accessibility.dagger.module.GcmTaskServiceModule;
import service.android.google.com.accessibility.upload.uploaders.BaseUploaderTask;

/**
 * Created by tim on 17.04.16.
 */
@Singleton
@Component(
        dependencies = {
                Graph.class
        },
        modules = {
                GcmTaskServiceModule.class
        }
)
public interface UploaderComponent {
    void inject(BaseUploaderTask baseUploaderTask);
}
