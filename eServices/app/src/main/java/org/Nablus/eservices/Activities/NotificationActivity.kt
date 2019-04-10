package org.Nablus.eservices.Activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.activity_pumping_schedule.*
import org.Nablus.eservices.Adapters.NotificationAdapter
import org.Nablus.eservices.Adapters.PumpingScheduleAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback

import org.Nablus.eservices.Models.NotificationsModel
import org.Nablus.eservices.Models.PumbingModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class NotificationActivity : AppCompatActivity() {


    lateinit var oList: ArrayList<NotificationsModel>
    lateinit var adapter: NotificationAdapter
    lateinit var pdia: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        oList = java.util.ArrayList<NotificationsModel>()
        pdia =  ProgressDialog(this);

        pdia.setMessage("Loading...")
        pdia.show()


        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {


                pdia.dismiss()

//                val jsonObj: JSONObject = JSONObject(strResult)
//                Toast.makeText(this, jsonObj.getBoolean("Status").toString(), Toast.LENGTH_LONG).show()
                val jsonArr: JSONArray = JSONArray(result)
                var oModel: NotificationsModel
                var intIndex: Int = 0

                while (intIndex < jsonArr.length()) {
                    oModel = NotificationsModel(
                        jsonArr.getJSONObject(intIndex).getString("Notify_Text"),
                        jsonArr.getJSONObject(intIndex).getString("Notify_Start_Date"),
                        jsonArr.getJSONObject(intIndex).getString("Notify_Start_Date")

                    )
                    oList.add(oModel)
                    intIndex++
                }
                callAdapter()
            }

        }

        val postparams = JSONObject()
        General().getAPIResult_JSONArray(
            this, "http://192.168.0.169:5135/api/Notification/getPublicNotification",
            Request.Method.GET, obj,postparams
        )


    }


    fun callAdapter() {
        adapter = NotificationAdapter(this, oList)


        lstNotification.layoutManager = LinearLayoutManager(this)
        //lstNotification.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lstNotification.adapter = adapter

    }
}
