package com.raja.roomdatabase.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.raja.roomdatabase.R
import com.raja.roomdatabase.databinding.FragmentLoginBinding
import com.raja.roomdatabase.utils.UtilKotlin
import com.raja.roomdatabase.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        UtilKotlin.setFont(
            binding.tvDoYouHaveAccount, 27, 35, "You don't have an account? Register"
        )
        return binding.root
    }
    override fun onResume() {
        super.onResume()

        /*User try to login in App
        * */

        binding.btnLoginUser.setOnClickListener {
            val strUsername = binding.etUSerName.text.toString().trim()
            val strPassword = binding.etUSerPassword.text.toString().trim()

            if (strUsername.isEmpty()) {
                binding.etUSerName.error = "Please enter your username"
            } else if (strPassword.isEmpty()) {
                binding.etUSerPassword.error = "Please enter your password"
            } else {
/*
                viewModel.getLoginDetails(requireContext(), strUsername)!!.observe(this, Observer {
                    if (it == null) {
                        UtilKotlin.showMessage(requireContext(), "User Not Found")
                        clearText()
                    } else {
                        UtilKotlin.showMessage(requireContext(), "User Found Successfully")
                        clearText()
                        val intent = Intent(requireContext(), HomeActivity::class.java)
                        startActivity(intent)
                    }
                })
*/
            }
        }
//...........................................
        binding.btnHomeScreen.setOnClickListener {
            val intent = Intent(requireContext(), HomeActivity::class.java)
            startActivity(intent)
        }
    }


}