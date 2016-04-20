package service.android.google.com.accessibility.upload;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.ArrayList;
import java.util.List;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.upload.uploaders.BaseUploaderTask;

/**
 * Created by tim on 14.04.16.
 */
public class UploaderHelper {

    private final Prefser prefser;
    private final Resources resources;
    private final UploaderFactory uploaderFactory;
    private final List<BaseUploaderTask> uploaderTasks = new ArrayList<>();

    public UploaderHelper(final Prefser prefser,
                          final Resources resources,
                          final UploaderFactory uploaderFactory) {
        this.prefser = prefser;
        this.resources = resources;
        this.uploaderFactory = uploaderFactory;
    }

    private void getUploaderTasks() {
        final boolean isFTPSelected = prefser.get(resources.getString(R.string.pref_key_FTP), Boolean.class, false);
        final boolean isEmailSelected = prefser.get(resources.getString(R.string.pref_key_email), Boolean.class, false);

        uploaderTasks.add(uploaderFactory.createFTPUploaderTask(isFTPSelected));
        uploaderTasks.add(uploaderFactory.createEmailUploaderTask(isEmailSelected));
    }

    public void registerTasks() {
        getUploaderTasks();

        for (BaseUploaderTask uploaderTask : uploaderTasks) {
            uploaderTask.registerTask();
        }
    }
}