package com.raja.roomdatabase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raja.roomdatabase.data.LoginTableModel

//@Database(entities = [LoginTableModel::class], version = 1, exportSchema = false)
@Database(entities = arrayOf(LoginTableModel::class), version = 1, exportSchema = false)
abstract class RoomSingleton : RoomDatabase() {
    abstract fun loginDao(): DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: RoomSingleton? = null

        fun getDataseClient(context: Context): RoomSingleton {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, RoomSingleton::class.java, "myData.db")
                    .allowMainThreadQueries()
                    .build()

                return INSTANCE!!

            }

        }
    }
}