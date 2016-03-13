package service.android.google.com.accessibility.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class ChatMessage implements Parcelable {

    public static Builder builder() {
        return new AutoParcel_ChatMessage.Builder();
    }

    public abstract Person person();

    public abstract String text();

    @Nullable
    public abstract String date();

    public abstract Builder toBuilder();

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder person(final Person person);

        public abstract Builder text(final String text);

        public abstract Builder date(final String date);

        public abstract ChatMessage build();
    }
}