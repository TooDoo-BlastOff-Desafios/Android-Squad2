package br.com.toodoo.fipay.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {

    companion object {

        private var auth: FirebaseAuth? = null
        private var dbRef: DatabaseReference? = null

        fun getFirebaseAuth(): FirebaseAuth {
            if (auth == null) {
                auth = FirebaseAuth.getInstance()
            }

            return auth as FirebaseAuth
        }

        fun getDbReference(): DatabaseReference {
            if (dbRef == null) {
                dbRef = FirebaseDatabase.getInstance().reference
            }

            return dbRef as DatabaseReference
        }

    }

}