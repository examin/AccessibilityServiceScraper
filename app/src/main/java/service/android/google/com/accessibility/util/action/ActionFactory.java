package service.android.google.com.accessibility.util.action;

import nl.nl2312.rxcupboard.RxDatabase;

/**
 * Created by tim on 26.03.16.
 */
public class ActionFactory {

    public ActionFactory() {
    }

    public SaveEventToDbFunction saveEventToDbAction(final RxDatabase rxDatabase) {
        return new SaveEventToDbFunction(rxDatabase);
    }

    public SaveChatEventToDbFunction saveChatEventToDbFunction(final RxDatabase rxDatabase) {
        return new SaveChatEventToDbFunction(rxDatabase);
    }
}