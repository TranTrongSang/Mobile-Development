package com.example.myproject_earningapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myproject_earningapps.Fragment.HistoryFragment
import com.example.myproject_earningapps.Fragment.HomeFragment
import com.example.myproject_earningapps.Fragment.ProfileFragment
import com.example.myproject_earningapps.Fragment.SpinFragment
import com.example.myproject_earningapps.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem




class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the initial fragment to HomeFragment
        replaceFragment(HomeFragment())

        // Set up the BottomNavigationView
        binding. { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.profileFragment -> replaceFragment(ProfileFragment())
                R.id.spinFragment -> replaceFragment(SpinFragment())
                R.id.historyFragment -> replaceFragment(HistoryFragment())
                else -> return@setOnItemSelectedListener false // Return false for unhandled item
            }
            true
        }


//        var Navcontroller = findNavController(R.id.fragmentContainerView);
//        var bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        bottomNav.setupWithNavController(Navcontroller)
    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        fragmentTransaction.commit()


    }
}

