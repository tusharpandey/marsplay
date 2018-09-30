package com.app.assignment.model.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.app.fragmentstack.dao.tables.AppPhotos;

@Database(entities = {AppPhotos.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDao();
}