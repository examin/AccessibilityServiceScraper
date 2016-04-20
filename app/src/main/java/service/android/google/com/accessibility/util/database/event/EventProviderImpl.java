package service.android.google.com.accessibility.util.database.event;

import java.util.List;

import nl.nl2312.rxcupboard.RxDatabase;
import rx.schedulers.Schedulers;
import service.android.google.com.accessibility.model.database.EventDTO;
import service.android.google.com.accessibility.util.database.AbstractProvider;

/**
 * Created by tim on 11.04.16.
 */
public class EventProviderImpl extends AbstractProvider implements EventProvider {

    public EventProviderImpl(final RxDatabase rxDatabase) {
        super(rxDatabase);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        return rxDatabase.query(EventDTO.class)
                .toList()
                .subscribeOn(Schedulers.io())
                .toBlocking()
                .first();
    }
}