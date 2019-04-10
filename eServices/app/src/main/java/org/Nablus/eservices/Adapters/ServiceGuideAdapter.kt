package org.Nablus.eservices.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_service_guide.view.*
import org.Nablus.eservices.Activities.NewsDetailsActivity
import org.Nablus.eservices.Activities.ServiceGuideDetailsActivity


import org.Nablus.eservices.Models.ServiceGuideModel
import org.Nablus.eservices.R


class ServiceGuideAdapter(val context: Context, var oList: List<ServiceGuideModel>) :
    RecyclerView.Adapter<ServiceGuideAdapter.TestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_service_guide, parent, false)
        return TestListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestListViewHolder, position: Int) {
        holder?.setData(oList[position], position)
    }

    override fun getItemCount(): Int {
        return oList.size
    }

    inner class TestListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Current_LatestNews: ServiceGuideModel? = null
        var Current_Position: Int? = 0

        init {
            itemView.setOnClickListener {
                ////////////////Open Detail Intent///////////////////



            }
// itemView.imgFlag.setOnClickListener {
// var intent :  Intent = Intent()
// intent.action = Intent.ACTION_SEND
// intent.putExtra(Intent.EXTRA_TEXT, Current_Test!!.CountryName)
// intent.type = "text/plain"
// context.startActivity(Intent.createChooser(intent, "Share To:"))
//}
        }

        fun setData(Test: ServiceGuideModel, pos: Int) {
// Set date to controls itemView.country_name.text = Country!!.CountryName
/* Assign Image to Image View// var resID :  Int
// ResId = context.getResources().getIdentifier(
// Country!!.Country_Flag,
// "drawable", "org.Nablus.eservices"
 )
 itemView.imgFlag.setImageResource(resID)
*/
            Current_LatestNews = oList[pos]
            Current_Position = pos
            itemView.tvService_Number.text = Current_LatestNews!!.Service_Number
            itemView.tvService_Department.text=Current_LatestNews!!.Service_Department
            itemView.tvService_Type.text = Current_LatestNews!!.Service_Type
            itemView.tvService_workTime.text=Current_LatestNews!!.Service_Time

            itemView.btnService_Details.setOnClickListener{

                var intent = Intent(context, ServiceGuideDetailsActivity::class.java).also {

                   it.putExtra("Service_Number", Current_LatestNews?.Service_Number)
                  it.putExtra("Service_Department", Current_LatestNews?.Service_Department)
                  it.putExtra("Service_Type", Current_LatestNews?.Service_Type)
                   it.putExtra("Service_Time", Current_LatestNews?.Service_Time)
                    it.putExtra("Service_OdrdrId", Current_LatestNews?.Service_OdrdrId)
                }

                ContextCompat.startActivity(context, intent, null)

            }

        }
    }

    fun updateList(onewList: List<ServiceGuideModel>) {
        oList = ArrayList<ServiceGuideModel>()
        (oList as ArrayList<ServiceGuideModel>).addAll(onewList)
        notifyDataSetChanged()
    }
}
