package service.android.google.com.accessibility.model;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class ChatEvent implements Parcelable, ASEvent {

    public static Builder builder() {
        return new AutoParcel_ChatEvent.Builder();
    }

    public abstract String packageName();

    public abstract String messages();

    public abstract String contactName();

    public abstract Builder toBuilder();

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder packageName(final String packageName);

        public abstract Builder messages(final String messages);

        public abstract Builder contactName(final String contactName);

        public abstract ChatEvent build();

    }
}
