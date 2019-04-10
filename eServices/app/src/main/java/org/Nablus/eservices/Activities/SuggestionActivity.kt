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
import kotlinx.android.synthetic.main.activity_suggestion.*
import org.Nablus.eservices.Adapters.PumpingScheduleAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback
import org.Nablus.eservices.Models.PumbingModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class SuggestionActivity : AppCompatActivity() {

    lateinit var pdia: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion)

        pdia =  ProgressDialog(this)

    }


    fun btnSendSuggestion_Click(view: View){

        var SuggestionTitel = edSuggestion_title.text.toString()
        var SuggestionDetails = edSuggestion_Details.text.toString()

        if ((SuggestionTitel.isEmpty()) || (SuggestionDetails.isEmpty())){

            val s = "الرجاء ادخال رقم كافة البيانات"
            Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()

        }

        else{

            postSuggestion(SuggestionTitel,SuggestionDetails)
        }

    }




    fun postSuggestion(SuggestionTitel:String,SuggestionDetails:String ){

        pdia.setMessage("Loading...")
        pdia.show()
        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {


                pdia.dismiss()


                if (result.contains("Success")) {
                    Toast.makeText(
                        applicationContext, "تم ارسال اقتراحك بنجاح",
                        Toast.LENGTH_LONG
                    ).show()
                   edSuggestion_title.setText("")

                } else {
                    Toast.makeText(
                        applicationContext, "حدث خطا اثناء عملية الارسال ، حاول لاحقا",
                        Toast.LENGTH_LONG
                    ).show()
                  edSuggestion_Details.setText("")
                }





            }

        }



        val params = HashMap<String, String>()
        params["Suggestion_Title"] = "f"
        params["Sugestion_Desc"] = "f"
        val jsonArray =  JSONObject(
            params)

        General().getAPIResult_JSONArray(
            this, "http://192.168.0.169:5135/api/Services/AddSuggestion",
            Request.Method.POST, obj,jsonArray
        )


    }


    fun callAdapter() {


    }
}
