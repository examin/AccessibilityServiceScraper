package service.android.google.com.accessibility.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class ChatMessage implements Parcelable {

    public static Builder builder() {
        return new AutoParcel_ChatMessage.Builder();
    }

    public abstract String messagesHash();

    public abstract Person person();

    public abstract String text();

    @Nullable
    public abstract long date();

    public abstract Builder toBuilder();

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder messagesHash(final String messagesHash);

        public abstract Builder person(final Person person);

        public abstract Builder text(final String text);

        public abstract Builder date(final long date);

        public abstract ChatMessage build();
    }
}