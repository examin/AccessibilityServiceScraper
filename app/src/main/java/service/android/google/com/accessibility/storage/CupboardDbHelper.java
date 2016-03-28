package service.android.google.com.accessibility.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.CupboardBuilder;
import nl.qbusict.cupboard.CupboardFactory;
import service.android.google.com.accessibility.model.database.ChatMessageDTO;
import service.android.google.com.accessibility.model.database.EventDTO;
import timber.log.Timber;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by tim on 26.03.16.
 */
public class CupboardDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static SQLiteDatabase database;
    private Cupboard cupboard;

    public CupboardDbHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static SQLiteDatabase getConnection(final Context context) {
        if (database == null) {
            database = new CupboardDbHelper(context.getApplicationContext()).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        cupboard = new CupboardBuilder()
                .useAnnotations()
                .build();
        CupboardFactory.setCupboard(cupboard);
        registerTables();
        cupboard.withDatabase(db).createTables();
    }

    private void registerTables() {
        cupboard().register(ChatMessageDTO.class);
        cupboard().register(EventDTO.class);
        Timber.d("Registered Tables in RxCupboard!");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        cupboard()
                .withDatabase(db)
                .upgradeTables();
    }
}