package br.com.toodoo.fipay.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.toodoo.fipay.R

class SettingsActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var menuItemAccount: ConstraintLayout
    private lateinit var menuItemNotification: ConstraintLayout
    private lateinit var menuItemMyCard: ConstraintLayout
    private lateinit var menuItemSecurity: ConstraintLayout
    private lateinit var menuItemCurrency: ConstraintLayout
    private lateinit var menuItemServices: ConstraintLayout
    private lateinit var menuItemStatistics: ConstraintLayout
    private lateinit var menuItemLogout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btnBack = findViewById(R.id.btnBack)
        menuItemAccount = findViewById(R.id.menu_item_account)
        menuItemNotification = findViewById(R.id.menu_item_notification)
        menuItemMyCard = findViewById(R.id.menu_item_my_card)
        menuItemSecurity = findViewById(R.id.menu_item_security)
        menuItemCurrency = findViewById(R.id.menu_item_currency)
        menuItemServices = findViewById(R.id.menu_item_services)
        menuItemStatistics = findViewById(R.id.menu_item_statistics)
        menuItemLogout = findViewById(R.id.menu_item_logout)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        setMenuItemClickListeners()
    }

    private fun setMenuItemClickListeners() {
        menuItemAccount.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_account)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemNotification.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_notification)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemMyCard.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_my_card)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemSecurity.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_security)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemCurrency.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_currency)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemServices.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_services)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemStatistics.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_statistics)
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        menuItemLogout.setOnClickListener {
            val result = Intent()
            result.putExtra("id", R.id.menu_item_logout)
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}