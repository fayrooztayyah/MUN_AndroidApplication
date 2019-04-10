package org.Nablus.eservices.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_service_guide.view.*
import kotlinx.android.synthetic.main.row_service_guide_details.view.*
import org.Nablus.eservices.Activities.ServiceGuideDetailsActivity
import org.Nablus.eservices.Models.ServiceGuideDetailsModel
import org.Nablus.eservices.Models.ServiceGuideModel
import org.Nablus.eservices.R




class ServiceGuideDetailsAdapter(val context: Context, var oList: List<ServiceGuideDetailsModel>) :
    RecyclerView.Adapter<ServiceGuideDetailsAdapter.TestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_service_guide_details, parent, false)
        return TestListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestListViewHolder, position: Int) {
        holder?.setData(oList[position], position)
    }

    override fun getItemCount(): Int {
        return oList.size
    }

    inner class TestListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Current_LatestNews: ServiceGuideDetailsModel? = null
        var Current_Position: Int? = 0

        init {
            itemView.setOnClickListener {
                ////////////////Open Detail Intent///////////////////



            }


        }

        fun setData(Test: ServiceGuideDetailsModel, pos: Int) {


            Current_LatestNews = oList[pos]
            Current_Position = pos
           itemView.tvCost_Des.text = Current_LatestNews!!.Service_CostDetails
            itemView.tvCost_Value.text = Current_LatestNews!!.Service_CostValue
            itemView.tvCost_curancy.text = Current_LatestNews!!.Service_CostCurrancy
        }
    }

    fun updateList(onewList: List<ServiceGuideDetailsModel>) {
        oList = ArrayList<ServiceGuideDetailsModel>()
        (oList as ArrayList<ServiceGuideDetailsModel>).addAll(onewList)
        notifyDataSetChanged()
    }
}
