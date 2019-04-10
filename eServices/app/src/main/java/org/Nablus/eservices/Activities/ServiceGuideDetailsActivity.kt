package org.Nablus.eservices.Activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_service_guide.*
import kotlinx.android.synthetic.main.activity_service_guide_details.*
import org.Nablus.eservices.Adapters.ServiceGuideAdapter
import org.Nablus.eservices.Adapters.ServiceGuideDetailsAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback

import org.Nablus.eservices.Models.ServiceGuideDetailsModel
import org.Nablus.eservices.Models.ServiceGuideModel

import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class ServiceGuideDetailsActivity : AppCompatActivity() {

    lateinit var oList: ArrayList<ServiceGuideDetailsModel>
    lateinit var adapter: ServiceGuideDetailsAdapter
    lateinit var pdia: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_guide_details)

        oList = java.util.ArrayList<ServiceGuideDetailsModel>()
        pdia =  ProgressDialog(this);

        val Service_Number = getIntent().getStringExtra("Service_Number");
        val Service_Department = getIntent().getStringExtra("Service_Department");
        val Service_Type = getIntent().getStringExtra("Service_Type");
        val Service_Time = getIntent().getStringExtra("Service_Time");
        val Service_OdrdrId = getIntent().getStringExtra("Service_OdrdrId");


        tvService_Number.text = Service_Number
        tvService_department.text = Service_Department
        tvService_Type.text = Service_Type
        tvService_WorkingTime.text = Service_Time

        getCostsDetails(Service_OdrdrId)

    }



    fun  getCostsDetails(orderId: String){



        pdia.setMessage("Loading...")
        pdia.show()


        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {
                pdia.dismiss()

                if (result != null) {


                    var reader: JSONObject = JSONObject(result)

                    if (reader.getString("Order_Type_Fees") == "null") {


                    }

                    else{

                        Log.d("Order_Type_Fees",reader.getString("Order_Type_Fees"))

                        val jsonArr: JSONArray = reader.getJSONArray("Order_Type_Fees");


                        var oModel: ServiceGuideDetailsModel
                        var intIndex: Int = 0

                        while (intIndex < jsonArr.length()) {

                            oModel = ServiceGuideDetailsModel(

                                jsonArr.getJSONObject(intIndex).getString("Fee_Type_Desc"),
                                jsonArr.getJSONObject(intIndex).getString("Fee_Type_Amount"),
                                jsonArr.getJSONObject(intIndex).getString("Currency_Name")

                            )
                            oList.add(oModel)
                            intIndex++
                        }
                        callAdapter()
                    }
                }
            }
            }

            val jsonObject = JSONObject();

            General().getAPIResult_JSONObject(
            this, "http://192.168.0.169:5135/api/ServiceGuide/getServiceGuide/" + orderId,
            Request.Method.GET, obj
            )



    }

    fun callAdapter() {
        adapter = ServiceGuideDetailsAdapter(this, oList)


        lstServiceGuide_Costs.layoutManager = LinearLayoutManager(this)
        lstServiceGuide_Costs.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lstServiceGuide_Costs.adapter = adapter

    }



}
