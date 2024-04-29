package com.radmanhayati.cafebazar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class, RemoteKeys::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {

    abstract val dao: MovieDao
    abstract val getRemoteKeysDao: RemoteKeysDao
}