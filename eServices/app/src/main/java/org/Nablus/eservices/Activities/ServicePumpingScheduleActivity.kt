package org.Nablus.eservices.Activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_pumping_schedule.*
import kotlinx.android.synthetic.main.activity_service_pumping_schedule.*

import org.Nablus.eservices.Adapters.ServicePumpingScheduleAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback

import org.Nablus.eservices.Models.ServicePumpingScheduleModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject




class ServicePumpingScheduleActivity : AppCompatActivity() {

    lateinit var oList: ArrayList<ServicePumpingScheduleModel>
    lateinit var adapter: ServicePumpingScheduleAdapter
    var initSumNumber = 0
    lateinit var pdia: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(org.Nablus.eservices.R.layout.activity_service_pumping_schedule)
        oList = java.util.ArrayList<ServicePumpingScheduleModel>()
        pdia =  ProgressDialog(this)

        tvPlace_Name.setVisibility(View.INVISIBLE);
        tvPlace_Lable.setVisibility(View.INVISIBLE);
        imItem_Place.setVisibility(View.INVISIBLE);

 }



    fun getPumpingSchedual(view: View){


        if (edSubscribeNumber.text.toString().isEmpty()) {
            val s = "الرجاء ادخال رقم الاشتراك"
            Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()

        } else {

            initSumNumber = Integer.parseInt(edSubscribeNumber.text.toString())
            pdia.setMessage("Loading...")
            pdia.show()

            val obj = object : VolleyCallback {
                override fun onSuccessResponse(result: String) {

                    pdia.dismiss()

                    //Parent_Region_Desc

//                val jsonObj: JSONObject = JSONObject(strResult)
//                Toast.makeText(this, jsonObj.getBoolean("Status").toString(), Toast.LENGTH_LONG).show()
                    val jsonArr: JSONArray = JSONArray(result)
                    var oModel: ServicePumpingScheduleModel
                    var intIndex: Int = 0

                    while (intIndex < jsonArr.length()) {
                        oModel = ServicePumpingScheduleModel(
                            jsonArr.getJSONObject(intIndex).getString("Schedule_Status_Desc"),
                            jsonArr.getJSONObject(intIndex).getString("Actual_Start_Date"),
                            jsonArr.getJSONObject(intIndex).getString("Actual_Start_Date"),
                            jsonArr.getJSONObject(intIndex).getString("Actual_End_Date")
                        )
                        oList.add(oModel)
                        intIndex++
                    }

                    if (oList.size == 0){
                        val s = "عذرا ، رقم الاشتراك المدخل غير صحيح"
                        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
                    }

                    else{


                        if (tvPlace_Lable.getVisibility() == View.INVISIBLE){
                            tvPlace_Lable.setVisibility(View.VISIBLE);
                        }

                        if (imItem_Place.getVisibility() == View.INVISIBLE){
                            imItem_Place.setVisibility(View.VISIBLE);
                        }



                        if (tvPlace_Name.getVisibility() == View.INVISIBLE){
                            tvPlace_Name.setVisibility(View.VISIBLE)
                            tvPlace_Name.text = jsonArr.getJSONObject(0).getString("Parent_Region_Desc")
                        }



                        callAdapter()
                    }

                }

            }
            val postparams = JSONObject()

            General().getAPIResult_JSONArray(
                this, "http://192.168.0.169:5135/api/Water/PumpingSchedule/Agreement_No?Agreement_No=" + initSumNumber,
                Request.Method.GET, obj,postparams
            )

        }




    }

    fun callAdapter() {
        adapter = ServicePumpingScheduleAdapter(this, oList)


        lstServiceSchedule.layoutManager = LinearLayoutManager(this)
        lstServiceSchedule.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lstServiceSchedule.adapter = adapter

    }
}
