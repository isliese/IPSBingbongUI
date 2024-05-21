package com.example.ipsbingbongui;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ImageEntity image);

    @Query("SELECT * FROM image_table")
    LiveData<List<ImageEntity>> getAllImages();
}
