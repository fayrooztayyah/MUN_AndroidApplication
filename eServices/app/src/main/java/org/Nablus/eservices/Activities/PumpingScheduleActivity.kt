package org.Nablus.eservices.Activities

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.RelativeLayout
import android.widget.Toast
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_pumping_schedule.*
import org.Nablus.eservices.Adapters.PumpingScheduleAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback
import org.Nablus.eservices.Models.PumbingModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class PumpingScheduleActivity : AppCompatActivity() {


    lateinit var oList: ArrayList<PumbingModel>
    lateinit var adapter: PumpingScheduleAdapter
    lateinit var pdia: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pumping_schedule)
        oList = java.util.ArrayList<PumbingModel>()
        pdia =  ProgressDialog(this);

        pdia.setMessage("Loading...")
        pdia.show()

        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {


                pdia.dismiss()

//                val jsonObj: JSONObject = JSONObject(strResult)
//                Toast.makeText(this, jsonObj.getBoolean("Status").toString(), Toast.LENGTH_LONG).show()
                val jsonArr: JSONArray = JSONArray(result)
                var oModel: PumbingModel
                var intIndex: Int = 0

                while (intIndex < jsonArr.length()) {
                    oModel = PumbingModel(
                        jsonArr.getJSONObject(intIndex).getString("Parent_Region_Desc"),
                        jsonArr.getJSONObject(intIndex).getString("Schedule_Status_Desc"),
                        jsonArr.getJSONObject(intIndex).getString("Actual_Start_Date"),
                         jsonArr.getJSONObject(intIndex).getString("Actual_Start_Date")
                    )
                    oList.add(oModel)
                    intIndex++
                }
                callAdapter()
            }

        }

        val jsonObject = JSONObject();

        General().getAPIResult_JSONArray(
            this, "http://192.168.0.169:5135/api/Water/ParentRegionPumpingSchedule",
            Request.Method.GET, obj,jsonObject
        )

    }

    fun callAdapter() {
        adapter = PumpingScheduleAdapter(this, oList)


        lstSchedule.layoutManager = LinearLayoutManager(this)
        lstSchedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lstSchedule.adapter = adapter

    }
}



