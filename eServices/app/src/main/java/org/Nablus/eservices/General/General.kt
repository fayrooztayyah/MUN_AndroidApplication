package org.Nablus.eservices.General

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.Nablus.eservices.Models.Criteria_Model
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Method

var SPLASH_TIME_OUT: Long = 4000
val DATABASENAME = "ComplaintDB"
val DBVERSION = 1
lateinit var db: SQLiteDatabase

public interface VolleyCallback {
    abstract fun onSuccessResponse(result: String)
    operator fun invoke(strResp: String) {

    }
}

class General {


    public fun getAPIResult_JSONArray(context: Context, URL: String, method: Int, callback: VolleyCallback): String {
        //val URL = "http://data.daral-aalam.ps/API/SrearchBook"
        //val criteria = "كنز"
        //val criteria_Model: Criteria_Model = Criteria_Model(Field_Name = "Book_Title", Field_Value = criteria)
        val requestQueue = Volley.newRequestQueue(context)
        //var jsonObject: JSONObject = JSONObject()
        var jsonObject: JSONArray = JSONArray()
        //jsonObject.put("Field_Name", "Book_Title")
        //jsonObject.put("Field_Value", criteria)
        var result: String = ""
        //        var objectRequest = JsonObjectRequest(
        var objectRequest = JsonArrayRequest(
            //Request.Method.POST,
            method,
            URL,
            jsonObject,
            Response.Listener {

                callback.onSuccessResponse(it.toString())
            },
            Response.ErrorListener {
                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                Log.d("Error", it.toString())
            })
        val comparable = try {
            requestQueue.add(objectRequest)
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_LONG).show()
            Log.d("Error", ex.message.toString())
        }
        return result// objectRequest.toString()
    }

    public fun getAPIResult_JSONObject(context: Context, URL: String, method: Int, callback: VolleyCallback): String {
        //val URL = "http://data.daral-aalam.ps/API/SrearchBook"
        //val criteria = "كنز"
        //val criteria_Model: Criteria_Model = Criteria_Model(Field_Name = "Book_Title", Field_Value = criteria)
        val requestQueue = Volley.newRequestQueue(context)
        var jsonObject: JSONObject = JSONObject()
        //var jsonObject: JSONArray = JSONArray()
        //jsonObject.put("Field_Name", "Book_Title")
        //jsonObject.put("Field_Value", criteria)
        var result: String = ""
        var objectRequest = JsonObjectRequest(
            //var objectRequest =  JsonArrayRequest(
            //Request.Method.POST,
            method,
            URL,
            jsonObject,
            Response.Listener {

                callback.onSuccessResponse(it.toString())
            },
            Response.ErrorListener {
                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                Log.d("Error", it.toString())
            })
        val comparable = try {
            requestQueue.add(objectRequest)
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_LONG).show()
            Log.d("Error", ex.message.toString())
        }
        return result// objectRequest.toString()
    }

}