package com.example.mobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*  // For accessing views

class MainActivity : AppCompatActivity() {

    private var isFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            isFragmentA = savedInstanceState.getBoolean("isFragmentA", true)
        }

        if (isFragmentA) {
            loadFragment(FragmentA())
        } else {
            loadFragment(FragmentB())
        }

        switchButton.setOnClickListener {
            if (isFragmentA) {
                loadFragment(FragmentB())
            } else {
                loadFragment(FragmentA())
            }

            isFragmentA = !isFragmentA

            switchButton.text = if (isFragmentA) "Switch to FragmentB" else "Switch to FragmentA"
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isFragmentA", isFragmentA)
    }
}
