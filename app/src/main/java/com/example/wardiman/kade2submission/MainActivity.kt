package com.example.wardiman.kade2submission

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.wardiman.kade2submission.R.id.nav_button
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    private  var Id_league = "4331"
    private var Position = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_button.setOnNavigationItemSelectedListener {
                item -> when(item.itemId){
            R.id.navigation_prev -> {
                Position = 1
                openFragment(MatchFragment.newInstance(Position, Id_league))
                return@setOnNavigationItemSelectedListener true
            }

            R.id.navigation_next -> {
                Position = 2
                openFragment(MatchFragment.newInstance(Position, Id_league))
                return@setOnNavigationItemSelectedListener true
            }
            R.id.navigation_fav -> {
                loadFavoritesFragment(savedInstanceState)
            }
        }
            false
        }
        openFragment(MatchFragment.newInstance(Position,Id_league))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()

    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

}
