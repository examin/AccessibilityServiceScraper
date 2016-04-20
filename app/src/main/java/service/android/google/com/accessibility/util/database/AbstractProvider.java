package service.android.google.com.accessibility.util.database;

import nl.nl2312.rxcupboard.RxDatabase;

/**
 * Created by tim on 17.04.16.
 */
public class AbstractProvider {

    protected final RxDatabase rxDatabase;

    public AbstractProvider(final RxDatabase rxDatabase) {
        this.rxDatabase = rxDatabase;
    }
}