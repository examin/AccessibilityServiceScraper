package service.android.google.com.accessibility.application;

import android.app.Application;
import android.support.graphics.drawable.BuildConfig;

import com.crashlytics.android.Crashlytics;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import service.android.google.com.accessibility.dagger.component.DaggerGraph;
import service.android.google.com.accessibility.dagger.component.Graph;
import service.android.google.com.accessibility.util.crashlytics.CrashlyticsTree;
import timber.log.Timber;

public class AccessibilityApplication extends Application {

    private static AccessibilityApplication instance;
    @Inject
    CrashlyticsTree crashlyticsTree;
    @Inject
    Crashlytics crashlytics;
    private Graph graph;

    public AccessibilityApplication(){
        instance = this;
    }

    public static AccessibilityApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        doDaggerInjection();
        setupCrashlytics();
    }

    private void setupCrashlytics() {
        Fabric.with(this, crashlytics);
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        Timber.plant(crashlyticsTree);
    }

    private void doDaggerInjection() {
        graph = DaggerGraph.Initializer.init(this);
        graph.inject(this);
    }

    public Graph graph() {
        return graph;
    }
}