package me.theawesomegem.testapp.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by TheAwesomeGem on 1/13/2018.
 */

@Entity(tableName = "video")
public class VideoModel {

    public enum VideoGenre{
        HORROR("Horror"),
        COMEDY("Comedy"),
        ROMANCE("Romance"),
        DRAMA("Drama"),
        THRILLER("Thriller"),
        MYSTERY("Mystery"),
        DOCUMENTARY("Documentary");

        String readableName;

        VideoGenre(String readableName)
        {
            this.readableName = readableName;
        }

        @Override
        public String toString() {
            return this.readableName;
        }
    }

    @NonNull
    @PrimaryKey
    private String name;

    @NonNull
    private String url;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "popularity")
    private int popularity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    /*
    public VideoModel(String name, VideoGenre genre, int popularity){
        this.name = name;
        this.genre = genre;
        this.popularity = popularity;
    }*/
}
