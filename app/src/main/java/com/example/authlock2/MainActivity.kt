package com.example.authlock2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.lock.AuthenticationCallback
import com.auth0.android.lock.Lock
import com.auth0.android.result.Credentials

class MainActivity : AppCompatActivity() {

    private lateinit var lock: Lock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val account = Auth0(this)

        lock = Lock.newBuilder(account, callback)
            // Customize Lock
            .build(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Important! Release Lock and its resources
        lock.onDestroy(this)
    }

    override fun onStart() {
        super.onStart()
        startActivity(lock.newIntent(this))
    }

    private val callback = object : AuthenticationCallback() {
        override fun onAuthentication(credentials: Credentials) {
            // Authenticated
        }

        override fun onError(error: AuthenticationException) {
            // Exception occurred
        }
    }
}