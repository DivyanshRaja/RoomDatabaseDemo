package com.raja.roomdatabase.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.raja.roomdatabase.R
import com.raja.roomdatabase.adapter.AdapterUser
import com.raja.roomdatabase.data.LoginTableModel
import com.raja.roomdatabase.databinding.ActivityHomeBinding
import com.raja.roomdatabase.databinding.CustomDialogBinding
import com.raja.roomdatabase.utils.ClickListener
import com.raja.roomdatabase.viewmodel.LoginViewModel

class HomeActivity : AppCompatActivity(), ClickListener {

    private lateinit var viewModel: LoginViewModel
    private var binding: ActivityHomeBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
// Observe the model
        viewModel.allUsers.observe(this, Observer { users ->
            // Data bind the recycler view
            val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            dividerItemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.bg_recycler_divider_modify_list
                )!!
            )
            binding?.recyclerViewUsers?.addItemDecoration(dividerItemDecoration)
            binding?.recyclerViewUsers?.adapter = AdapterUser(users,this)
        })

    }

    /*
    Click on recyclerView Adapter items
    * */
    override fun onUserListClickListener(user: LoginTableModel, isDelete: Boolean) {
        if (isDelete) {
            showCustomDialog(this, user)
        } else {
            val fragment = UpdateUserFragment()
            addFragmentToActivity(fragment, user)
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun showCustomDialog(context: Context, selectedUser: LoginTableModel) {
        val dialogBinding: CustomDialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.custom_dialog,
                null,
                false
            )

        val customDialog = AlertDialog.Builder(context, 0).create()
        customDialog.apply {
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding?.btnCancel?.setOnClickListener {
            customDialog.dismiss()
        }

        /*
        Click on Yes Button to delete selected user
        * */
        dialogBinding?.btnYes?.setOnClickListener {
            viewModel.deleteUser(selectedUser)
            customDialog.dismiss()
        }
        customDialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()
    }

    /*
    Update User records
    * */
    private fun addFragmentToActivity(fragment: Fragment?, user: LoginTableModel) {
        fragment?.let {
            val bundle = Bundle()
            bundle.putParcelable("userInfo", user)
            fragment.arguments = bundle
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentMain, it, "UpdateUserDialogFragment")
                .addToBackStack("null")
                .commit()
        }
    }


}