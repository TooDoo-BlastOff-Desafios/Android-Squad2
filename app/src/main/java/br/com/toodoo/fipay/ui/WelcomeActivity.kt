package br.com.toodoo.fipay.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.WindowCompat
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.ui.authentication.SignInActivity
import br.com.toodoo.fipay.ui.authentication.SignUpActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        findViewById<Button>(R.id.btnGoToSignIn).setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

        findViewById<Button>(R.id.btnGoToSignUp).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}