package org.Nablus.eservices.Activities

import android.app.DownloadManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun btnEnterWithoutLogin_Click(view: View){
        val intent:Intent=Intent(this,PublicServicesActivity::class.java)
        startActivity(intent)
    }
    fun btnHasAccount_Click(view:View){
        var strResult:String=""
        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {
                strResult= result
            }
        }
        val postparams = JSONObject()
        val strTest:String =  General().getAPIResult_JSONArray(this,"http://192.168.0.169:5135/api/Water/ParentRegionPumpingSchedule",Request.Method.GET,obj,postparams)
        Toast.makeText(this,strResult  + "dddddd" ,Toast.LENGTH_LONG).show()
    }



}

