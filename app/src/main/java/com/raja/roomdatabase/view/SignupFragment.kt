package com.raja.roomdatabase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.raja.roomdatabase.R
import com.raja.roomdatabase.databinding.FragmentSignupBinding
import com.raja.roomdatabase.utils.UtilKotlin
import com.raja.roomdatabase.viewmodel.LoginViewModel


class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        UtilKotlin.setFont(
            binding.tvGoLoginScreen, 25, 30, "Do you have an account ? Login"
        )

        binding.tvGoLoginScreen.setOnClickListener {
            val fragment = LoginFragment()
            addFragmentToActivity(fragment)
        }

        /*
        Insert Data in Room Database
        * */
        binding.btnRegisterUser.setOnClickListener {
            if (isUSerEmpty()) {
                viewModel.addNewUser(
                    binding.etUSerName.text.toString(),
                    binding.etUSerEmail.text.toString(),
                    binding.etUSerPassword.text.toString()
                )
                UtilKotlin.showMessage(requireContext(), "Data inserted successfully !")
                clearUserInputField()
            } else {
                UtilKotlin.showMessage(requireContext(), "Please enter all the details of User")
            }
        }
    }

    private fun addFragmentToActivity(fragment: Fragment?) {
        fragment?.let {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, it, "MyFragment")
                .addToBackStack("null")
                .commit()
        }
    }

    /*
    Check input field are empty or not
    * */
    private fun isUSerEmpty(): Boolean {
        return viewModel.isEntryValid(
            binding.etUSerName.text.toString(),
            binding.etUSerEmail.text.toString(),
            binding.etUSerPassword.text.toString()
        )
    }

    /**
     * Clear EditTexts make it empty
     */
    private fun clearUserInputField() {
        binding.etUSerName.text?.clear()
        binding.etUSerEmail.text?.clear()
        binding.etUSerPassword.text?.clear()
    }

}