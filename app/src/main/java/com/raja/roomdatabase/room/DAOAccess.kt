package com.raja.roomdatabase.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raja.roomdatabase.data.LoginTableModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insert(student: LoginTableModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getUser(username: String?): LiveData<LoginTableModel>

    @Query("SELECT * FROM Login")
    fun getListOfUser(): LiveData<List<LoginTableModel>>

    @Delete
    fun delete(user: LoginTableModel)

    @Update
    fun update(user: LoginTableModel): Int

}