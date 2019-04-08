package org.Nablus.eservices.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.Nablus.eservices.R


import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_previous_complaints.*
import org.Nablus.eservices.Adapters.ComplaintListAdapter
import org.Nablus.eservices.DAL.Complaint_DAL
import org.Nablus.eservices.General.db
import org.Nablus.eservices.Models.ComplaintModel


class PreviousComplaintsActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var oList: ArrayList<ComplaintModel>
    lateinit var adapter: ComplaintListAdapter
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var userInput: String = newText!!.toLowerCase()
        var newList = ArrayList<ComplaintModel>()
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
        setContentView(R.layout.activity_previous_complaints)

        var oList = java.util.ArrayList<ComplaintModel>()

        //oList = Complaint_DAL(db).GetComplaintList()

        var oModel: ComplaintModel

        for (i in 0..9) {
            oModel=ComplaintModel( i,"Test Add","","","Test Complaint",  false)
            oList.add(oModel)
        }

        adapter = ComplaintListAdapter(this, oList)
        lstComplaints.layoutManager = LinearLayoutManager(this)
        lstComplaints.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
/*        menuInflater.inflate(R.menu.toolbar_menu, menu)
        var menuItem :   MenuItem = menu!!.findItem(R.id.action_search)
        var searchView :   SearchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)*/
        return true
    }
}
