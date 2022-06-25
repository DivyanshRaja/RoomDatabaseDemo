package com.raja.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.raja.roomdatabase.R
import com.raja.roomdatabase.data.LoginTableModel
import com.raja.roomdatabase.databinding.UserListBinding
import com.raja.roomdatabase.utils.ClickListener

class AdapterUser(val user: List<LoginTableModel>, private val onClickListener: ClickListener) :
    RecyclerView.Adapter<AdapterUser.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val binding = DataBindingUtil.inflate<UserListBinding>(
            LayoutInflater.from(parent.context), R.layout.user_list, parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(user[position])
        /*
        Delete User Details
        * */
        holder.binding.ivDeleteUSer.setOnClickListener {
            onClickListener.onUserListClickListener(user[position], true)
        }

        /*
        Update user
        * */
        holder.binding.ivEditUsersRecords.setOnClickListener {
            onClickListener.onUserListClickListener(user[position], false)
        }

    }

    override fun getItemCount(): Int {
        return user.size
    }

    inner class UserViewHolder(val binding: UserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: LoginTableModel) {
            with(binding) {
                tvUserName.text = user.Username
                tvUserEmail.text = user.Email
            }
        }

    }
}