package infix.studios.wallpapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //val navController = this.findNavController(R.id.nav_host)
        //NavigationUI.setupActionBarWithNavController(this, navController)
        setupViews()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.nav_host)
//        return navController.navigateUp()
//    }

    fun setupViews() {
        // Finding the Navigation Controller
        var navController = findNavController(R.id.fragNavHost)

        // Setting Navigation Controller with the BottomNavigationView
        bottomNavView.setupWithNavController(navController)

        // Setting Up ActionBar with Navigation Controller
        // Pass the IDs of top-level destinations in AppBarConfiguration
        var appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf (
                R.id.homeFragment,
                R.id.categoriesFragment,
                R.id.searchFragment,
                R.id.favoriteFragment
            )
        )

        // Setting Up ActionBar with Navigation Controller
        setupActionBarWithNavController(navController)
    }
}
