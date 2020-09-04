package infix.studios.wallpapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    fun showBottomNavigation() {
        bottomNavView.visibility = VISIBLE
    }

    fun hideBottomNavigation() {
        bottomNavView.visibility = GONE
    }

    private fun setupViews() {
        // Finding the Navigation Controller
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        // Setting Up ActionBar with Navigation Controller
        // Pass the IDs of top-level destinations in AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.categoriesFragment,
                R.id.searchFragment,
                R.id.favoriteFragment
            )
        )

        // Setting Up ActionBar with Navigation Controller
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragNavHost)
        return navController.navigateUp()
    }
}
