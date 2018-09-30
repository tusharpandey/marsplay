package com.app.assignment.model.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.app.fragmentstack.dao.tables.AppPhotos

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPictures(list: MutableList<AppPhotos>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(appPhotos: AppPhotos)

    @Query("SELECT COUNT(*) FROM Pictures")
    fun getPicturesCount(): Int

    @Query("select * from Pictures WHERE picId BETWEEN :arg0 AND :arg1 order by picId ASC")
    fun getPicturesNumber(arg0: Int, arg1: Int): MutableList<AppPhotos>
}
