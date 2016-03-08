package service.android.google.com.accessibility.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.accessibility.AccessibilityNodeInfo;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Event implements Parcelable {

    public static Builder builder() {
        return new AutoParcel_Event.Builder();
    }

    abstract int eventType();

    abstract AccessibilityNodeInfo source();

    abstract String className();

    abstract String packageName();

    abstract long eventTime();

    abstract String text();

    abstract boolean isEnabled();

    abstract boolean isPassword();

    abstract boolean isChecked();

    @Nullable
    abstract int fromIndex();

    @Nullable
    abstract int addedCount();

    @Nullable
    abstract int removedCount();

    @Nullable
    abstract String beforeText();

    @Nullable
    abstract String contentDescription();

    public abstract Builder toBuilder();

    @Override
    public String toString() {
        return this.eventTime() + ": [" + this.className() + "] " + this.eventType() + " :" + this.text();
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

        public abstract Builder addedCount(final int count);

        public abstract Builder removedCount(final int count);

        public abstract Builder beforeText(final String beforeText);

        public abstract Builder contentDescription(final String contentDescription);

        public abstract Event build();
    }
}