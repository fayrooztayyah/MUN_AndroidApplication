package org.Nablus.eservices.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.Nablus.eservices.General.SPLASH_TIME_OUT
import org.Nablus.eservices.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val runnable = Runnable() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        Handler().postDelayed(runnable, SPLASH_TIME_OUT)
    }
}
