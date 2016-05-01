package service.android.google.com.accessibility.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import auto.parcel.AutoParcel;

/**
 * Created by Tim Rijckaert on 30/04/2016.
 */
@AutoParcel
public abstract class Notification implements Parcelable, ASEvent {

    public static Builder builder() {
        return new AutoParcel_Notification.Builder();
    }

    ;

    public abstract long eventTime();

    public abstract String packageName();

    @Nullable
    public abstract String category();

    @Nullable
    public abstract String extraText();

    @Nullable
    public abstract String extraTitle();

    @Nullable
    public abstract String extraInfoText();

    @Nullable
    public abstract String extraSubText();

    @Nullable
    public abstract String extraSummaryText();

    public abstract Builder toBuilder();

    @Override
    public String toString() {
        return String.format("%d: notification: [%s %s %s]", this.eventTime(), packageName(), extraTitle(), extraText());
    }

    @AutoParcel.Builder
    public abstract static class Builder {

        public abstract Builder eventTime(final long eventType);

        public abstract Builder packageName(final String packageName);

        public abstract Builder category(final String category);

        public abstract Builder extraText(final String extraText);

        public abstract Builder extraTitle(final String extraTitle);

        public abstract Builder extraInfoText(final String extraInfoText);

        public abstract Builder extraSubText(final String extraSubText);

        public abstract Builder extraSummaryText(final String extraSummaryText);

        public abstract Notification build();
    }
}
