package me.theawesomegem.testapp.data.repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import me.theawesomegem.testapp.data.model.VideoModel;

/**
 * Created by TheAwesomeGem on 1/15/2018.
 */

@Dao
public interface VideoDao {
    @Query("SELECT COUNT(*) FROM video where name = :name")
    int videoCount(String name);

    @Query("SELECT * FROM video")
    List<VideoModel> getAll();

    @Query("SELECT * FROM video WHERE name IN (:names)")
    List<VideoModel> loadAllByNames(String[] names);

    @Query("SELECT * FROM video WHERE name IN (:name) AND genre IN (:genre)")
    List<VideoModel> loadAllByNamesAndGenre(String name, String genre);

    @Query("SELECT * FROM video WHERE genre IN (:genre)")
    List<VideoModel> loadAllByGenre(String genre);

    @Query("SELECT * FROM video WHERE genre LIKE :genre LIMIT 1")
    VideoModel findByGenre(String genre);

    @Insert
    void insertAll(VideoModel... videos);

    @Delete
    void delete(VideoModel video);
}
