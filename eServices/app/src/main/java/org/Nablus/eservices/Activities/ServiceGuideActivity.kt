package org.Nablus.eservices.Activities

import android.app.ProgressDialog
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.SearchView;
import android.widget.Toast
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_service_guide.*

import org.Nablus.eservices.Adapters.ServiceGuideAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback

import org.Nablus.eservices.Models.ServiceGuideModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLDecoder


class ServiceGuideActivity : AppCompatActivity() , SearchView.OnQueryTextListener{





    lateinit var oList: ArrayList<ServiceGuideModel>
    lateinit var adapter: ServiceGuideAdapter
    lateinit var pdia: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_guide)

        oList = java.util.ArrayList<ServiceGuideModel>()
        pdia =  ProgressDialog(this);

        pdia.setMessage("Loading...")
        pdia.show()
        ServiceGuideSearch.setOnQueryTextListener(this);


        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {


                pdia.dismiss()

//                val jsonObj: JSONObject = JSONObject(strResult)
//                Toast.makeText(this, jsonObj.getBoolean("Status").toString(), Toast.LENGTH_LONG).show()
                val jsonArr: JSONArray = JSONArray(result)
                var oModel: ServiceGuideModel
                var intIndex: Int = 0

                while (intIndex < jsonArr.length()) {
                    var workTime = ""
                    if ((jsonArr.getJSONObject(intIndex).getString("General_Case_Expected_Time") == (jsonArr.getJSONObject(intIndex).getString("Special_Case_Expected_Time"))))

                    {
                       workTime =  jsonArr.getJSONObject(intIndex).getString("General_Case_Expected_Time" ) +  "يوم"
                    }
                    else{
                        workTime = jsonArr.getJSONObject(intIndex).getString("General_Case_Expected_Time" ) + "-" +  jsonArr.getJSONObject(intIndex).getString("Special_Case_Expected_Time") +  "يوم"
                    }
                    oModel = ServiceGuideModel(

                        jsonArr.getJSONObject(intIndex).getString("Order_Type_ID"),
                        jsonArr.getJSONObject(intIndex).getString("Order_Type_No"),
                        jsonArr.getJSONObject(intIndex).getString("Department_Name"),
                        jsonArr.getJSONObject(intIndex).getString("Order_Type_Desc"),
                        workTime
                    )
                    oList.add(oModel)
                    intIndex++
                }
                callAdapter()
            }

        }

        val jsonObject = JSONObject();

        General().getAPIResult_JSONArray(
            this, "http://192.168.0.169:5135/api/ServiceGuide/getOrderTypeBriefList",
            Request.Method.GET, obj,jsonObject
        )

    }

    fun callAdapter() {
        adapter = ServiceGuideAdapter(this, oList)


        lstServiceGuide.layoutManager = LinearLayoutManager(this)
        lstServiceGuide.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lstServiceGuide.adapter = adapter

    }




    override fun onQueryTextSubmit(query: String): Boolean {


       var searchKey =  ServiceGuideSearch.query.toString()
        pdia.setMessage("Loading...")
        pdia.show()

        getServiceGuideData(searchKey)


        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {

        return false
    }



    fun getServiceGuideData(searchKey:String){

        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {

                pdia.dismiss()

         if (result.equals("[]")){
             Toast.makeText(
                 applicationContext, "لا يوجد بيانات متوفرة ",
                 Toast.LENGTH_LONG
             ).show()

         }

             else{
             oList.clear()

                val jsonArr: JSONArray = JSONArray(result)
                var oModel: ServiceGuideModel
                var intIndex: Int = 0

                while (intIndex < jsonArr.length()) {
                    var workTime = ""
                    if ((jsonArr.getJSONObject(intIndex).getString("General_Case_Expected_Time") == (jsonArr.getJSONObject(intIndex).getString("Special_Case_Expected_Time"))))

                    {
                        workTime =  jsonArr.getJSONObject(intIndex).getString("General_Case_Expected_Time" ) +  "يوم"
                    }
                    else{
                        workTime = jsonArr.getJSONObject(intIndex).getString("General_Case_Expected_Time" ) + "-" +  jsonArr.getJSONObject(intIndex).getString("Special_Case_Expected_Time") +  "يوم"
                    }
                    oModel = ServiceGuideModel(
                        jsonArr.getJSONObject(intIndex).getString("Order_Type_ID"),
                        jsonArr.getJSONObject(intIndex).getString("Order_Type_No"),
                        jsonArr.getJSONObject(intIndex).getString("Department_Name"),
                        jsonArr.getJSONObject(intIndex).getString("Order_Type_Desc"),
                        workTime
                    )
                    oList.add(oModel)
                    intIndex++
                }
                callAdapter()

                }

            }

        }

        val jsonObject = JSONObject();
        var key = Uri.encode(searchKey)

        print("key"+key)
        Log.d("key",key)


        General().getAPIResult_JSONArray(
            this, "http://192.168.0.169:5135/api/ServiceGuide/getOrderTypeBriefList/" + key,
            Request.Method.GET, obj,jsonObject
        )
    }


}
