package com.example.appbanhang.Model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ItemEntity::class],
    version = 1
)
abstract class RoomDB : RoomDatabase() {
    abstract fun itemDB(): ItemDao
}