package service.android.google.com.accessibility.util.action;

import nl.nl2312.rxcupboard.RxDatabase;
import rx.functions.Action1;
import service.android.google.com.accessibility.model.ASEvent;

/**
 * Created by tim on 26.03.16.
 */
public abstract class AbstractSaveASEventToDbAction<M extends ASEvent, N> implements Action1<M> {
    protected final RxDatabase rxDatabase;

    public AbstractSaveASEventToDbAction(final RxDatabase rxDatabase) {
        this.rxDatabase = rxDatabase;
    }

    @Override
    public abstract void call(final M accessibilityEvent);

    public void save(final N mappedObject) {
        rxDatabase.put(mappedObject);
    }
}