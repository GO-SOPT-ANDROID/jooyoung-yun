package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnvMain.setOnItemSelectedListener {
            changeFragment(
                when (it.itemId) {
                    R.id.menu_home -> HomeFragment()
                    R.id.menu_search -> SearchFragment()
                    else -> GalleryFragment()
                }
            )
            true
        }
    }
    /*fragment 바꾸기*/
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
}