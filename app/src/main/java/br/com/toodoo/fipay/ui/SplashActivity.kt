package br.com.toodoo.fipay.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.FirebaseHelper
import java.util.*
import kotlin.concurrent.timerTask

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val isRemembered = sharedPreferences.getBoolean(getString(R.string.shp_remember_me), false)

        Timer().schedule(timerTask {
            if (isRemembered && FirebaseHelper.getFirebaseAuth().currentUser != null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                finish()
            }
        }, 1000)
    }

}