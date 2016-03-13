package service.android.google.com.accessibility.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.List;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class ChatEvent implements Parcelable, ASEvent {

    public static Builder builder() {
        return new AutoParcel_ChatEvent.Builder();
    }

    public abstract String packageName();

    @Nullable
    public abstract List<ChatMessage> messages();

    public abstract Builder toBuilder();

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder packageName(final String packageName);

        public abstract Builder messages(final List<ChatMessage> messages);

        public abstract ChatEvent build();
    }
}