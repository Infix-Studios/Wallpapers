package infix.studios.wallpapers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import infix.studios.wallpapers.util.ADDRESS
import infix.studios.wallpapers.util.SUBJECT
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
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

        navView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        navView.menu.findItem(R.id.contact_us_item).setOnMenuItemClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, ADDRESS)
                putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
            true
        }

        navView.menu.findItem(R.id.rate_us_item).setOnMenuItemClickListener {
            var manager: ReviewManager = ReviewManagerFactory.create(this)
            var reviewInfo: ReviewInfo? = null
            manager.requestReviewFlow().addOnCompleteListener { request ->
                if (request.isSuccessful) {
                    reviewInfo = request.result
                } else {
                    // Log error
                    Timber.d("Something went wrong with in app review")
                }
            }
            true
        }

        appBarConfig = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.categoriesFragment,
            R.id.searchFragment,
            R.id.favoriteFragment
        ).setOpenableLayout(drawer_layout).build()

        // Setting Up ActionBar with Navigation Controller
        setupActionBarWithNavController(navHostFragment.navController, appBarConfig)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragNavHost)
        return navController.navigateUp(appBarConfig)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
