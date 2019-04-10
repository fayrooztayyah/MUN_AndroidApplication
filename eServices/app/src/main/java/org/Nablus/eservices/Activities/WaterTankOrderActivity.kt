package org.Nablus.eservices.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_pumping_schedule.*
import kotlinx.android.synthetic.main.activity_pumping_schedule.view.*
import kotlinx.android.synthetic.main.activity_suggestion.*
import kotlinx.android.synthetic.main.activity_water_tank_order.*
import org.Nablus.eservices.Adapters.PumpingScheduleAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback
import org.Nablus.eservices.Models.PumbingModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class WaterTankOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_water_tank_order)



    }



    fun btnWaterTankOrder_Click(view: View){


        var hasWaterSupply = ch_HasWaterSupply.isChecked
        var ed_SubNumber =  ed_SubNumber.text.toString()
        var ed_WaterAmount = ed_WaterAmount.text.toString()
        var ed_MobileNumber = ed_MobileNumber.text.toString()
        var sp_BuildingType = sp_BuildingType.getSelectedItem().toString()
        var ed_SubStreet=  ed_SubStreet.text.toString()
        var ed_SubArea = ed_SubArea.text.toString()
        var ed_SubBuilding=   ed_SubBuilding.text.toString()
        var ed_landMark = ed_landMark.text.toString()
        var sp_SubType =   sp_SubType.getSelectedItem().toString()
        var ed_SubName= ed_SubName.text.toString()


        if (ed_SubNumber.isEmpty() || ed_WaterAmount.isEmpty() || ed_MobileNumber.isEmpty() || sp_BuildingType.isEmpty()
            || ed_SubStreet.isEmpty() || ed_SubArea.isEmpty() || ed_SubBuilding.isEmpty() || ed_landMark.isEmpty()
            || sp_SubType.isEmpty() || ed_SubName.isEmpty()){

            val s = "الرجاء ادخال رقم كافة البيانات"
            Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()

        }
        else{

            val obj = object : VolleyCallback {
                override fun onSuccessResponse(result: String) {

                    callAdapter(result)


                }

            }




            val params = HashMap<String, Any>()
            params["Agreement_No"] = Integer.parseInt(ed_SubNumber)
            params["Water_Amount"] = Integer.parseInt(ed_WaterAmount)
            params["Jawwal_No"] =ed_MobileNumber
            params["Building_Description"] = sp_BuildingType
            params["District"] = ed_SubStreet
            params["Street_Name"] = ed_SubArea
            params["Building_Name"] = ed_SubBuilding
            params["Building_Has_Water_Supply"] = hasWaterSupply
            params["Land_Mark"] = ed_landMark
            params["Citizen_Type_Desc"] =  sp_SubType
            params["Beneficiary_Citizen_Name"] = ed_SubName

            val jsonArray =  JSONObject(
                params)


            General().getAPIResult_JSONArray(
                this, "http://192.168.0.169:5135/api/WaterTank_Order",
                Request.Method.POST, obj,jsonArray
            )
        }





    }








    fun callAdapter(result: String) {



        Toast.makeText(
            applicationContext, result,
            Toast.LENGTH_LONG
        ).show()

       // if (result.contains("Success")) {
            Toast.makeText(
                applicationContext, "تم ارسال اقتراحك بنجاح",
                Toast.LENGTH_LONG
            ).show()


//        } else {
//            Toast.makeText(
//                applicationContext, "حدث خطا اثناء عملية الارسال ، حاول لاحقا",
//                Toast.LENGTH_LONG
//            ).show()
//
//        }
    }

}
