package com.raja.roomdatabase.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Login")
data class LoginTableModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "username")
    var Username: String,

    @ColumnInfo(name = "email")
    var Email: String,

    @ColumnInfo(name = "password")
    var Password: String
) : Parcelable
