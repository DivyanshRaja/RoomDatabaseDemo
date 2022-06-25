package com.raja.roomdatabase.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.raja.roomdatabase.R
import com.raja.roomdatabase.data.LoginTableModel
import com.raja.roomdatabase.databinding.FragmentUpdateUserBinding
import com.raja.roomdatabase.utils.UtilKotlin
import com.raja.roomdatabase.viewmodel.LoginViewModel


class UpdateUserFragment : Fragment() {

    private lateinit var binding: FragmentUpdateUserBinding
    private lateinit var viewModel: LoginViewModel

    lateinit var userName: String
    private lateinit var userEmail: String
    lateinit var userPassword: String

    var userIds: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_user, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bundle = arguments
        val user = bundle!!.getParcelable<LoginTableModel>("userInfo")
        if (user != null) {
            userIds = user.id
            userName = user.Username
            userEmail = user.Email
            userPassword = user.Password

        }
    }

    override fun onResume() {
        super.onResume()
        binding.etUSerName.setText(userName)
        binding.etUSerEmail.setText(userEmail)
        /*
        Click on Button to Update User in Room DB
        * */
        binding.btnUpdateUser.setOnClickListener {
            if (isEmptyUser()) {
                viewModel.updateUser(
                    userIds, binding.etUSerName.text.toString(),
                    binding.etUSerEmail.text.toString(), userPassword
                )
                UtilKotlin.showMessage(requireContext(), "User updated successfully!")
                activity?.onBackPressed()
            } else {
                UtilKotlin.showMessage(requireContext(), "Please enter all the details of User")
            }
        }
    }

    private fun isEmptyUser(): Boolean {
        return viewModel.isEnterValidForUpdateUser(
            binding.etUSerName.text.toString().trim(),
            binding.etUSerEmail.text.toString().trim()
        )
    }


}