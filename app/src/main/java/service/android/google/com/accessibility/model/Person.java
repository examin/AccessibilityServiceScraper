package service.android.google.com.accessibility.model;

import android.os.Parcelable;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class Person implements Parcelable {

    public static Builder builder() {
        return new AutoParcel_Person.Builder();
    }

    public abstract String fullName();

    public abstract Builder toBuilder();

    @AutoParcel.Builder
    public abstract static class Builder {
        public abstract Builder fullName(final String fullName);

        public abstract Person build();
    }
}