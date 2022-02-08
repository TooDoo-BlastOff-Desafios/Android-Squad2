package br.com.toodoo.fipay.helper

import android.util.Log
import br.com.toodoo.fipay.model.Deposit
import br.com.toodoo.fipay.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationHelper {



    companion object {
        private var loggedUser: User? = null

        fun getUser(): User? {
            return loggedUser
        }

        fun setUser(user: User) {
            loggedUser = user
        }

    }

}