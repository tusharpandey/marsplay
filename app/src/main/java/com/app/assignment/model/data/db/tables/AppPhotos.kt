package com.app.fragmentstack.dao.tables

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Pictures")
data class AppPhotos(
        @PrimaryKey(autoGenerate = true) var picId: Int = 0,
        @ColumnInfo(name = "path") var path: String = "none")