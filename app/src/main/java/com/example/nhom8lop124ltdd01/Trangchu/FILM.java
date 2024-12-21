package com.example.nhom8lop124ltdd01.Trangchu;

import android.os.Parcel;
import android.os.Parcelable;

public class FILM implements Parcelable {
    private String title;
    private String imageUrl; // Thay đổi từ int resourceId sang String imageUrl
    private Integer filmId; // Thêm trường filmId

    public FILM(String title, String imageUrl, Integer filmId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.filmId = filmId;

    }

    protected FILM(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        filmId = in.readInt();
    }

    public static final Creator<FILM> CREATOR = new Creator<FILM>() {
        @Override
        public FILM createFromParcel(Parcel in) {
            return new FILM(in);
        }

        @Override
        public FILM[] newArray(int size) {
            return new FILM[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeInt(filmId);
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public Integer getFilmId() {
        return filmId;
    }


}