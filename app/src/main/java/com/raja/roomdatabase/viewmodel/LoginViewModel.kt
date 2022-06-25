package com.raja.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.raja.roomdatabase.data.LoginTableModel
import com.raja.roomdatabase.room.RoomSingleton
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    /*
    Initialize RoomDatabase class
    * */
    private val db: RoomSingleton = RoomSingleton.getDataseClient(application)

    /**
     * Inserts the new Item into database.
     */
    fun addNewUser(userName: String, userEmail: String, userPassword: String) {
        val newUser = getNewUserEntry(userName, userEmail, userPassword)
        insertUser(newUser)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertUser(user: LoginTableModel) {
        viewModelScope.launch {
            db.loginDao().insert(user)
        }
    }

    /**
     * Returns an instance of the [LoginTableModel] entity class with the user info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewUserEntry(
        name: String,
        email: String,
        password: String
    ): LoginTableModel {
        return LoginTableModel(
            Username = name,
            Email = email,
            Password = password
        )
    }

    /*
  get all User from DB
  * */
    internal val allUsers: LiveData<List<LoginTableModel>> = db.loginDao().getListOfUser()


    /*
    Delete records from table
    * */
    fun deleteUser(user: LoginTableModel) {
        viewModelScope.launch {
            db.loginDao().delete(user)
        }
    }

    /**
     * Validation when user insert records in room database
     */
    fun isEntryValid(userName: String, userEmail: String, userPassword: String): Boolean {
        if (userName.isBlank() || userEmail.isBlank() || userPassword.isBlank()) {
            return false
        }
        return true
    }

    /*
    * Validation when user update records in room database
    * */
    fun isEnterValidForUpdateUser(name: String, email: String): Boolean {
        if (name.isBlank() || email.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Updates an existing Item in the database.
     */

    fun updateUser(
        userId: Int,
        username: String,
        userEmail: String,
        userPassword: String
    ) {
        val updatedUser = getUpdatedUserEntry(userId, username, userEmail, userPassword)
        updateUser(updatedUser)
    }

    /**
     * Launching a new coroutine to update an item in a non-blocking way
     */
    private fun updateUser(user: LoginTableModel) {
        viewModelScope.launch {
            db.loginDao().update(user)
        }
    }

    /**
     * Called to update an existing entry in the Inventory database.
     * Returns an instance of the [LoginTableModel] entity class with the user info updated by the user.
     */
    private fun getUpdatedUserEntry(
        UserId: Int,
        name: String,
        email: String,
        password: String
    ): LoginTableModel {
        return LoginTableModel(
            id = UserId,
            Username = name,
            Email = email,
            Password = password
        )
    }
}