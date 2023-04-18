package org.android.go.sopt.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fcv_main, HomeFragment.newInstance())
                .commit()
        }

    }


}