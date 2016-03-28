package service.android.google.com.accessibility.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.accessibility.AccessibilityNodeInfo;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Event implements Parcelable, ASEvent {

    public static Builder builder() {
        return new AutoParcel_Event.Builder();
    }

    public abstract int eventType();

    public abstract String className();

    public abstract String packageName();

    public abstract long eventTime();

    public abstract String text();

    public abstract boolean isEnabled();

    public abstract boolean isPassword();

    public abstract boolean isChecked();

    @Nullable
    public abstract AccessibilityNodeInfo source();

    @Nullable
    public abstract int fromIndex();

    @Nullable
    public abstract int toIndex();

    @Nullable
    public abstract int addedCount();

    @Nullable
    public abstract int removedCount();

    @Nullable
    public abstract int itemCount();

    @Nullable
    public abstract String beforeText();

    @Nullable
    public abstract String contentDescription();

    @Nullable
    public abstract int scrollX();

    @Nullable
    public abstract int scrollY();

    @Nullable
    public abstract Parcelable notificationParcel();

    @Nullable
    public abstract int currentItemIndex();

    public abstract Builder toBuilder();

    @Override
    public String toString() {
        // TODO: 12.03.16 Make a smarter toString() representation for different types.
        return this.eventTime() + ": [" + this.className() + "] " + this.eventType() + " : " + this.text();
    }

    @AutoParcel.Builder
    public abstract static class Builder {

        public abstract Builder eventType(final int eventType);

        public abstract Builder source(final AccessibilityNodeInfo nodeInfo);

        public abstract Builder className(final String className);

        public abstract Builder packageName(final String packageName);

        public abstract Builder eventTime(final long eventTime);

        public abstract Builder text(final String text);

        public abstract Builder isEnabled(final boolean isEnabled);

        public abstract Builder isPassword(final boolean isPassword);

        public abstract Builder isChecked(final boolean isChecked);

        public abstract Builder fromIndex(final int index);

        public abstract Builder toIndex(final int index);

        public abstract Builder itemCount(final int count);

        public abstract Builder addedCount(final int count);

        public abstract Builder removedCount(final int count);

        public abstract Builder beforeText(final String beforeText);

        public abstract Builder contentDescription(final String contentDescription);

        public abstract Builder scrollX(final int scrollX);

        public abstract Builder scrollY(final int scrollY);

        public abstract Builder notificationParcel(final Parcelable notificationPayLoad);

        public abstract Builder currentItemIndex(final int currentItemIndex);

        public abstract Event build();
    }
}