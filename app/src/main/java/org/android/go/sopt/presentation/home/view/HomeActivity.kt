package org.android.go.sopt.presentation.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.presentation.gallery.view.GalleryFragment
import org.android.go.sopt.presentation.search.SearchFragment
import org.android.go.sopt.util.binding.BindingActivity

class HomeActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragment(HomeFragment())


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