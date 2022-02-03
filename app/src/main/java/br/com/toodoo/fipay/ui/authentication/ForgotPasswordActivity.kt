package br.com.toodoo.fipay.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.FirebaseHelper

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val editEmail = findViewById<EditText>(R.id.editEmail)


        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnConfirm).setOnClickListener {
            val email = editEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                FirebaseHelper.getFirebaseAuth().sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Check your e-mail to reset the password.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
            } else {
                editEmail.error = "You must type an e-mail"
                editEmail.requestFocus()
            }
        }
    }
}