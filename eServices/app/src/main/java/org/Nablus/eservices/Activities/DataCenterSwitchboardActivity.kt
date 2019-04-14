package org.Nablus.eservices.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_center_switchboard.*
import org.Nablus.eservices.R

class DataCenterSwitchboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_center_switchboard)

        btnAboutUS.setOnClickListener(){
            val intent=Intent(this,AboutUsActivity::class.java)
            startActivity(intent)
        }

        BtnInformation.setOnClickListener(){
            val intent=Intent(this,DC_InformatiomActivity::class.java)
            startActivity(intent)
        }

    }
}
