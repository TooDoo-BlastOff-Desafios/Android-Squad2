package br.com.toodoo.fipay.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.FirebaseHelper
import br.com.toodoo.fipay.ui.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenSettings: ImageButton
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenSettings = findViewById(R.id.btnOpenSettings)
        toolbar = findViewById(R.id.toolbar)

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
        toolbar.title = "MyCard"
        fragmentTransaction.replace(R.id.navigation_host_fragment, MyCardFragment()).commit()
    }

    // Load the choosed fragment based on the menu item id
    private fun loadFragment(id: Int) {

        when (id) {
            R.id.menu_item_account -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "Account"
                fragmentTransaction.replace(R.id.navigation_host_fragment, AccountFragment()).commit()
            }
            R.id.menu_item_notification -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "Notification"
                fragmentTransaction.replace(R.id.navigation_host_fragment, NotificationsFragment()).commit()
            }
            R.id.menu_item_my_card -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "My Card"
                fragmentTransaction.replace(R.id.navigation_host_fragment, MyCardFragment()).commit()
            }
            R.id.menu_item_security -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "Security"
                fragmentTransaction.replace(R.id.navigation_host_fragment, SecurityFragment()).commit()
            }
            R.id.menu_item_currency-> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "Currency"
                fragmentTransaction.replace(R.id.navigation_host_fragment, CurrencyFragment()).commit()
            }
            R.id.menu_item_services -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "Services"
                fragmentTransaction.replace(R.id.navigation_host_fragment, ServicesFragment()).commit()
            }
            R.id.menu_item_statistics -> {
                fragmentTransaction = fragmentManager.beginTransaction()
                toolbar.title = "Statistics"
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