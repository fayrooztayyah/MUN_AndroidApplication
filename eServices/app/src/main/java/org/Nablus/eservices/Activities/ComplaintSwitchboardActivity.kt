package org.Nablus.eservices.Activities

import android.content.Intent
import android.media.tv.TvContract
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_complaint_switchboard.*
import org.Nablus.eservices.R

class ComplaintSwitchboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaint_switchboard)

        btnPreviousComplaint.setOnClickListener(){
            val intent=Intent(this,PreviousComplaintsActivity::class.java)
            startActivity(intent)
        }
        btnSubmitComplaint.setOnClickListener(){
            val intent = Intent(this, AddComplaintActivity::class.java)
            startActivity(intent)
        }
    }
}
