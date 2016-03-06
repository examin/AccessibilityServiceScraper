package service.android.google.com.accessibility.application;

import android.app.Application;
import android.support.graphics.drawable.BuildConfig;

import service.android.google.com.accessibility.dagger.component.DaggerGraph;
import service.android.google.com.accessibility.dagger.component.Graph;
import timber.log.Timber;

public class AccessibilityApplication extends Application {

    private static AccessibilityApplication instance;
    private Graph graph;

    public AccessibilityApplication(){
        this.instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
            Timber.tag("TAG");
        }

        graph = DaggerGraph.Initializer.init(this);
        graph.inject(this);
    }

    public Graph graph() {
        return graph;
    }

    public static AccessibilityApplication getInstance() {
        return instance;
    }
}