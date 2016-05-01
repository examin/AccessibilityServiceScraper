package service.android.google.com.accessibility.util.action;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.Notification;
import service.android.google.com.accessibility.model.database.NotificationDTO;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
public class SaveNotificationToDbFunction extends AbstractSaveASEventToDbAction<Notification, NotificationDTO> {

    public SaveNotificationToDbFunction(final RxDatabase rxDatabase) {
        super(rxDatabase);
    }

    @Override
    public void call(final Notification notification) {
        if (notification == null) {
            return;
        }
        this.save(new NotificationDTO(
                notification.eventTime(),
                notification.packageName(),
                notification.category(),
                notification.extraTitle(),
                notification.extraText(),
                notification.extraInfoText(),
                notification.extraSubText(),
                notification.extraSummaryText()
        ));
    }
}