package service.android.google.com.accessibility.dagger.module;

import android.app.Application;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.github.pwittchen.prefser.library.Prefser;
import com.google.android.gms.gcm.GcmNetworkManager;

import dagger.Module;
import dagger.Provides;
import nl.nl2312.rxcupboard.RxCupboard;
import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.storage.CupboardDbHelper;
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

    @Provides
    RxDatabase rxDatabase(final Application applicationContext) {
        final SQLiteDatabase db = new CupboardDbHelper(applicationContext)
                .getWritableDatabase();
        return RxCupboard.withDefault(db);
    }

    @Provides
    Prefser prefser() {
        return new Prefser(application);
    }

    @Provides
    Resources resources() {
        return application.getResources();
    }

    @Provides
    GcmNetworkManager gcmNetworkManager(){
        return GcmNetworkManager.getInstance(application);
    }
}