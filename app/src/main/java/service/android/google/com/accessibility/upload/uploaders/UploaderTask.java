package service.android.google.com.accessibility.upload.uploaders;

import java.io.File;

/**
 * Created by tim on 16.04.16.
 */
public interface UploaderTask {

    void registerTask();

    File getFileName();
}