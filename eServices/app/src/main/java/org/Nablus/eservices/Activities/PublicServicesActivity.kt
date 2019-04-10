package org.Nablus.eservices.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_public_services.*

import org.Nablus.eservices.Adapters.PublicServicesSwitchBoardListAdapter
import org.Nablus.eservices.Models.PublicServicesSwitchBoard_Model
import org.Nablus.eservices.R
import java.sql.Array

/*class PublicServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_services)
    }



}*/
class PublicServicesActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var oList: ArrayList<PublicServicesSwitchBoard_Model>
    lateinit var adapter: PublicServicesSwitchBoardListAdapter
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var userInput: String = newText!!.toLowerCase()
        var newList = ArrayList<PublicServicesSwitchBoard_Model>()
/* for ((CountryID, CountryName, Country_Flag,Flag_Usage_Year,Capital_City) in oList) {
if (CountryName.toLowerCase().contains(userInput))Then {
newList.add(CountryModel(CountryID,CountryName,Country_Flag,Flag_Usage_Year,Capital_City))
//          newList.add(CountryModel(CountryID,CountryName,Country_Flag,Flag_Usage_Year))
 }
 }
*/
        adapter.updateList(newList)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_services)
        val oList = ArrayList<PublicServicesSwitchBoard_Model>()

        //oList = Country_TopicsClass(db).GetCountry_TopicsList()
        oList.add(PublicServicesSwitchBoard_Model(0,"جدول الضخ-خدمة", "water", "#00574B"))
        oList.add(PublicServicesSwitchBoard_Model(1,"جدول الضخ", "water", "#008577"))
        oList.add(PublicServicesSwitchBoard_Model(2,"دليل الخدمات", "services", "#008577"))
        oList.add(PublicServicesSwitchBoard_Model(3,"طلب صهريج", "sehreg", "#00574B"))
        oList.add(PublicServicesSwitchBoard_Model(4,"اّخر اخبار البلدية", "last_news", "#00574B"))
        oList.add(PublicServicesSwitchBoard_Model(5,"الاشعارات", "notification", "#008577"))
        oList.add(PublicServicesSwitchBoard_Model(6,"الاقتراحات", "strike_filled_500", "#008577"))
        oList.add(PublicServicesSwitchBoard_Model(7,"الشكاوي", "strike_filled_500", "#00574B"))
        oList.add(PublicServicesSwitchBoard_Model(8,"مركز المعلومات ", "info", "#008577"))

        adapter = PublicServicesSwitchBoardListAdapter(this, oList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val manager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
       // recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = manager
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /*   menuInflater.inflate(R.menu.toolbar_menu, menu)
           var menuItem :   MenuItem = menu!!.findItem(R.id.action_search)
           var searchView :   SearchView = menuItem.actionView as SearchView
           searchView.setOnQueryTextListener(this)*/
        return true
    }
}

