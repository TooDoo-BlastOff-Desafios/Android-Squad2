package br.com.toodoo.fipay.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsetsController
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.FirebaseHelper
import br.com.toodoo.fipay.ui.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenSettings: ImageButton
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var toolbarContainer: LinearLayoutCompat
    private lateinit var toolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenSettings = findViewById(R.id.btnOpenSettings)
        toolbarContainer = findViewById(R.id.toolbarContainer)
        toolbarTitle = findViewById(R.id.toolbarTitle)

        val settingsSelectedItem = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                data?.let {
                    val id = it.getIntExtra("id", 0)
                    loadFragment(id)
                }
            }
        }

        btnOpenSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            settingsSelectedItem.launch(intent)
        }

        // Init fragmentManager and set the MyCardFragment to be load when the activity is created
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        toolbarTitle.text = "MyCard"
        fragmentTransaction.replace(R.id.navigation_host_fragment, MyCardFragment()).commit()
    }

    private fun resetToolbarColors() {
        toolbarContainer.setBackgroundColor(Color.WHITE)
        toolbarTitle.setTextColor(ContextCompat.getColor(this, R.color.neutral_700))
        btnOpenSettings.drawable.setTint(ContextCompat.getColor(this, R.color.neutral_700))
        window.statusBarColor = Color.WHITE
    }

    // Load the choosed fragment based on the menu item id
    private fun loadFragment(id: Int) {

        when (id) {
            R.id.menu_item_account -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "Account"
                resetToolbarColors()
                fragmentTransaction.replace(R.id.navigation_host_fragment, AccountFragment()).commit()
            }
            R.id.menu_item_notification -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "Notification"
                resetToolbarColors()
                fragmentTransaction.replace(R.id.navigation_host_fragment, NotificationsFragment()).commit()
            }
            R.id.menu_item_my_card -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "My Card"
                resetToolbarColors()
                fragmentTransaction.replace(R.id.navigation_host_fragment, MyCardFragment()).commit()
            }
            R.id.menu_item_security -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "Security"
                resetToolbarColors()
                fragmentTransaction.replace(R.id.navigation_host_fragment, SecurityFragment()).commit()
            }
            R.id.menu_item_currency-> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "Currency"
                resetToolbarColors()
                fragmentTransaction.replace(R.id.navigation_host_fragment, CurrencyFragment()).commit()
            }
            R.id.menu_item_services -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "Services"
                resetToolbarColors()
                fragmentTransaction.replace(R.id.navigation_host_fragment, ServicesFragment()).commit()
            }
            R.id.menu_item_statistics -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbarTitle.text = "Statistics"
                toolbarContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
                toolbarTitle.setTextColor(Color.WHITE)
                btnOpenSettings.drawable.setTint(Color.WHITE)
                window.statusBarColor = ContextCompat.getColor(this, R.color.purple_700)
                fragmentTransaction.replace(R.id.navigation_host_fragment, StatisticsFragment()).commit()
            }
            R.id.menu_item_logout -> {
                val sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean(getString(R.string.shp_remember_me), false)
                editor.apply()


                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
                FirebaseHelper.getFirebaseAuth().signOut()
            }
        }

    }

}