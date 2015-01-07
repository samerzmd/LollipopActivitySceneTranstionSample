package com.sam.lollipopactivitytranstionsample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sam on 1/7/2015.
 */
class BirdInfo implements Parcelable {
    public BirdInfo(String name, String role, int imageRes){
        this.name=name;this.role=role;this.imageRes=imageRes;
    }
    String name;
    String role;
    int imageRes;

    protected BirdInfo(Parcel in) {
        name = in.readString();
        role = in.readString();
        imageRes = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(role);
        dest.writeInt(imageRes);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BirdInfo> CREATOR = new Parcelable.Creator<BirdInfo>() {
        @Override
        public BirdInfo createFromParcel(Parcel in) {
            return new BirdInfo(in);
        }

        @Override
        public BirdInfo[] newArray(int size) {
            return new BirdInfo[size];
        }
    };
}