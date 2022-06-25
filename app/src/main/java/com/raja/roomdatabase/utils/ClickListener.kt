package com.raja.roomdatabase.utils

import com.raja.roomdatabase.data.LoginTableModel


interface ClickListener {
    fun onUserListClickListener(user: LoginTableModel, isDeleteOrUpdate: Boolean)
}