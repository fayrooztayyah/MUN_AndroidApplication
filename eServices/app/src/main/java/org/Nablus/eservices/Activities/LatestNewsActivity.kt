package org.Nablus.eservices.Activities

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_latest_news.*
import kotlinx.android.synthetic.main.activity_pumping_schedule.*
import org.Nablus.eservices.Adapters.LatestNewsListAdapter
import org.Nablus.eservices.Adapters.PumpingScheduleAdapter
import org.Nablus.eservices.General.General
import org.Nablus.eservices.General.VolleyCallback
import org.Nablus.eservices.Models.ComplaintModel
import org.Nablus.eservices.Models.LatestNewsModel
import org.Nablus.eservices.Models.PumbingModel
import org.Nablus.eservices.R
import org.json.JSONArray
import org.json.JSONObject

class LatestNewsActivity : AppCompatActivity() {


    lateinit var oList: ArrayList<LatestNewsModel>
    lateinit var adapter: LatestNewsListAdapter
    lateinit var pdia: ProgressDialog


//    override fun onQueryTextSubmit(query: String?): Boolean {
//        return false
//    }
//
//    override fun onQueryTextChange(newText: String?): Boolean {
//        var userInput: String = newText!!.toLowerCase()
//        var newList = ArrayList<LatestNewsModel>()
//
//        adapter.updateList(newList)
//        return true
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_news)

        oList = java.util.ArrayList<LatestNewsModel>()



        pdia =  ProgressDialog(this);

        pdia.setMessage("Loading...")
        pdia.show()

        val obj = object : VolleyCallback {
            override fun onSuccessResponse(result: String) {

                pdia.dismiss()

                 var reader: JSONObject =  JSONObject(result)




                val jsonArr: JSONArray = reader.getJSONArray("Result_Object");




                var oModel: LatestNewsModel
                var intIndex: Int = 0

                while (intIndex < jsonArr.length()) {
                    oModel = LatestNewsModel(
                        jsonArr.getJSONObject(intIndex).getString("News_Title"),
                        jsonArr.getJSONObject(intIndex).getString("News_Date"),
                        jsonArr.getJSONObject(intIndex).getString("News_Image_URL"),
                        jsonArr.getJSONObject(intIndex).getString("News_Details")
                    )
                    oList.add(oModel)
                    intIndex++
                }
                callAdapter()
            }

        }


        General().getAPIResult_JSONArray(
            this, "http://192.168.0.169:5135/api/Portal/getNewsList",
            Request.Method.GET, obj
        )

 }




    fun callAdapter() {

        adapter = LatestNewsListAdapter(this, oList)
        lstLatestNews.layoutManager = LinearLayoutManager(this)
        lstLatestNews.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        lstLatestNews.adapter = adapter

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
/*        menuInflater.inflate(R.menu.toolbar_menu, menu)
        var menuItem :   MenuItem = menu!!.findItem(R.id.action_search)
        var searchView :   SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)*/
        return true
    }

}

