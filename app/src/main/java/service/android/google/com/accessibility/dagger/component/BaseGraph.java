package service.android.google.com.accessibility.dagger.component;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.android.gms.gcm.GcmNetworkManager;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.application.AccessibilityApplication;

public interface BaseGraph {

    void inject(AccessibilityApplication accessibilityApplication);

    RxDatabase rxDatabase();

    Prefser prefser();

    Resources resources();

    GcmNetworkManager gcmNetworkManager();
}