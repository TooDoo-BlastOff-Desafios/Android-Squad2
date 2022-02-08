package br.com.toodoo.fipay.ui.authentication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import br.com.toodoo.fipay.R
import br.com.toodoo.fipay.helper.AuthenticationHelper
import br.com.toodoo.fipay.helper.FiPayApiHelper
import br.com.toodoo.fipay.helper.FirebaseHelper
import br.com.toodoo.fipay.model.User
import br.com.toodoo.fipay.ui.MainActivity
import br.com.toodoo.fipay.ui.WelcomeActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.timerTask

class SignInActivity : AppCompatActivity() {

    private lateinit var signInLayout: View
    private lateinit var editEmail: TextView
    private lateinit var editPassword: TextView
    private lateinit var cbRememberMe: CheckBox
    private lateinit var progressBar: ProgressBar

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        user = User()

        initComponents()

        setClickListeners()
    }

    private fun initComponents() {
        signInLayout = findViewById(R.id.siginLayout)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        cbRememberMe = findViewById(R.id.cbRememberMe)
        progressBar = findViewById(R.id.progressBar)

        sharedPreferences = getSharedPreferences(getString(R.string.pref_key), Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    private fun setClickListeners() {
        findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            validateFields()
        }

        findViewById<TextView>(R.id.txtForgotPassword).setOnClickListener {
            startActivity(Intent(this@SignInActivity, ForgotPasswordActivity::class.java))
        }

        findViewById<TextView>(R.id.textSignUp).setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }


        cbRememberMe.setOnClickListener {
            editor.putBoolean(getString(R.string.shp_remember_me), cbRememberMe.isChecked)
            editor.apply()
        }
    }

    private fun validateFields() {
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {
                progressBar.visibility = View.VISIBLE

                user.email = email
                user.password = password

                signIn()
            } else {
                editPassword.error = "You must type a password"
            }
        } else {
            editEmail.error = "You must type an e-mail"
            editEmail.requestFocus()

        }
    }

    private fun signIn() {
        FirebaseHelper.getFirebaseAuth().signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (emailIsVerified()) {
                        getUserData(user.email)
                    } else {
                        val snackbar = Snackbar.make(signInLayout, "Please verify your e-mail address.", Snackbar.LENGTH_LONG)
                        snackbar.setBackgroundTint(getColor(R.color.purple_700))
                        snackbar.setTextColor(getColor(R.color.white))
                        snackbar.show()

                        FirebaseHelper.getFirebaseAuth().signOut()
                        progressBar.visibility = View.GONE
                    }
                } else {
                    it.exception?.let { exception ->
                        val snackbar = Snackbar.make(signInLayout, exception.message!!, Snackbar.LENGTH_LONG)
                        snackbar.setBackgroundTint(getColor(R.color.purple_700))
                        snackbar.setTextColor(getColor(R.color.white))
                        snackbar.show()
                    }
                    progressBar.visibility = View.GONE
                }
            }
    }

    private fun getUserData(userEmail: String?) {
        if (userEmail != null) {

            GlobalScope.launch(Dispatchers.IO) {
                val callback = FiPayApiHelper.endpoint.getUsers()
                val response = callback.execute()
                val userData = response.body()?.filter { it.email == userEmail }

                if (userData != null) {
                    AuthenticationHelper.setUser(userData[0])
                    val intent = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    FirebaseHelper.getFirebaseAuth().signOut()
                    Toast.makeText(this@SignInActivity, "Something is wrong. Please try again later.", Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        } else {
            FirebaseHelper.getFirebaseAuth().signOut()
            Toast.makeText(this@SignInActivity, "Something is wrong. Please try again later.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun emailIsVerified(): Boolean {
        FirebaseHelper.getFirebaseAuth().currentUser?.let {
            return it.isEmailVerified
        }

        return false
    }
}