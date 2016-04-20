package service.android.google.com.accessibility.util.database.event;

import java.util.List;

import service.android.google.com.accessibility.model.database.EventDTO;

/**
 * Created by tim on 11.04.16.
 */
public interface EventProvider {
    List<EventDTO> getAllEvents();
}