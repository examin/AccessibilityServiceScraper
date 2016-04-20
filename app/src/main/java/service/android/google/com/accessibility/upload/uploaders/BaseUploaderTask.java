package service.android.google.com.accessibility.upload.uploaders;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.gcm.TaskParams;

import java.io.File;

import javax.inject.Inject;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.application.AccessibilityApplication;
import service.android.google.com.accessibility.dagger.component.DaggerUploaderComponent;
import service.android.google.com.accessibility.dagger.module.GcmTaskServiceModule;
import service.android.google.com.accessibility.upload.UploadType;
import service.android.google.com.accessibility.util.converter.ConverterHelper;
import timber.log.Timber;

/**
 * Created by tim on 15.04.16.
 */
public class BaseUploaderTask extends GcmTaskService implements UploaderTask {

    @Inject
    protected Prefser prefser;
    @Inject
    protected Resources resources;
    @Inject
    GcmNetworkManager networkManager;
    @Inject
    ConverterHelper converterHelper;
    boolean isServiceEnabled;
    private Class<? extends GcmTaskService> serviceType;

    public BaseUploaderTask(final Class<? extends GcmTaskService> serviceType,
                            final boolean isServiceEnabled) {
        this.serviceType = serviceType;
        this.isServiceEnabled = isServiceEnabled;
    }

    @Override
    public void onCreate() {
        doDaggerInjection();
        super.onCreate();
    }

    private String getPreferredUploadFrequency() {
        String uploadFrequency = "-1";
        if (prefser != null) {
            uploadFrequency = prefser.get(
                    resources.getString(R.string.pref_key_sync_frequency),
                    String.class,
                    "-1"
            );
        }
        return uploadFrequency;
    }

    private UploadType getPreferredUploadType() {
        return UploadType.getUploadTypeById(
                prefser.get(
                        resources.getString(R.string.pref_key_upload_format),
                        String.class,
                        "0"
                )
        );
    }

    @Override
    public File getFileName() {
        final UploadType preferredUploadType = getPreferredUploadType();
        return converterHelper.getFileName(preferredUploadType);
    }

    protected void handleException(final Exception e, final File fileToUpload) {
        Timber.e(e.getMessage());
        removeFile(fileToUpload);
    }

    protected void removeFile(final File fileToUpload) {
        if (fileToUpload != null) {
            Timber.d("File was deleted: " + fileToUpload.delete());
        }
    }

    private void doDaggerInjection() {
        DaggerUploaderComponent.builder()
                .gcmTaskServiceModule(new GcmTaskServiceModule(this))
                .graph(AccessibilityApplication.getInstance().graph())
                .build()
                .inject(this);
    }

    @Override
    public void registerTask() {
        doDaggerInjection();
        final String uploadFrequencyInMinutes = getPreferredUploadFrequency();

        if (uploadFrequencyInMinutes.equals("-1") || !isServiceEnabled) {
            cancelTask();
            return;
        }

        final int period = Integer.parseInt(uploadFrequencyInMinutes) * 60;
        networkManager.schedule(
                new PeriodicTask.Builder()
                        .setService(serviceType)
                        .setTag(serviceType.getCanonicalName())
                        .setPeriod(period)
                        .setPersisted(true)
                        .setRequiresCharging(true)
                        .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                        .build()
        );
        Timber.d("BaseUploaderTask: Task " + serviceType.getCanonicalName() + " successfully scheduled");
    }

    private void cancelTask() {
        if (networkManager != null) {
            networkManager.cancelTask(serviceType.getCanonicalName(), serviceType);
            Timber.d("BaseUploaderTask: Task " + serviceType.getCanonicalName() + " successfully cancelled");
        }
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        return GcmNetworkManager.RESULT_SUCCESS;
    }
}