package com.raja.roomdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.raja.roomdatabase.view.SignupFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        val fragment = SignupFragment()
        addFragmentToActivity(fragment)
    }

    private fun addFragmentToActivity(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragContainer, it, "MyFragment")
                .addToBackStack("null")
                .commit()
        }
    }

}