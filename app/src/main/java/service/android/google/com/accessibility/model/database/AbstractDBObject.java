package service.android.google.com.accessibility.model.database;

/**
 * Created by tim on 27.03.16.
 */
public abstract class AbstractDBObject implements DBObject {

    private Long _id;

    public AbstractDBObject() {
    }

    @Override
    public Long getId() {
        return this._id;
    }

    @Override
    public void setId(Long _id) {
        this._id = _id;
    }
}