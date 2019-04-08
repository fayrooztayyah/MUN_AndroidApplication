package org.Nablus.eservices.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_previous_complaints.view.*
import org.Nablus.eservices.Models.ComplaintModel
import org.Nablus.eservices.R



class ComplaintListAdapter(val context: Context, var oList: List<ComplaintModel>) :RecyclerView.Adapter<ComplaintListAdapter.ComplaintListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_previous_complaints, parent, false)
        return ComplaintListViewHolder(view)
    }
    override fun onBindViewHolder(holder: ComplaintListViewHolder, position: Int) {
        holder?.setData(oList[position], position)
    }
    override fun getItemCount(): Int {
        return oList.size
    }
    inner class ComplaintListViewHolder( itemView:  View) : RecyclerView.ViewHolder(itemView) {
        var Current_Complaint : ComplaintModel? = null
        var Current_Position :  Int? = 0
        init {
            itemView.setOnClickListener {
                ////////////////Open Detail Intent///////////////////
/*
var intent = intent(context, <DetailsActivityName>: Class.java)
 ContextCompat.startActivity(context, intent, null)
*/
            }
// itemView.imgFlag.setOnClickListener {
// var intent :  Intent = Intent()
// intent.action = Intent.ACTION_SEND
// intent.putExtra(Intent.EXTRA_TEXT, Current_Complaint!!.CountryName)
// intent.type = "text/plain"
// context.startActivity(Intent.createChooser(intent, "Share To:"))
//}
        }
        fun setData(Complaint : ComplaintModel, pos: Int) {
// Set date to controls itemView.country_name.text = Country!!.CountryName
/* Assign Image to Image View// var resID :  Int
// ResId = context.getResources().getIdentifier(
// Country!!.Country_Flag,
// "drawable", "org.Nablus.eservices"
 )
 itemView.imgFlag.setImageResource(resID)
*/
            Current_Complaint = oList[pos]
            Current_Position = pos
            itemView.tvComplaintAddress.text=Current_Complaint!!.Complaint_Address
        }
    }
    fun updateList(onewList:  List<ComplaintModel>) {
        oList = ArrayList<ComplaintModel>()
        (oList as ArrayList<ComplaintModel>).addAll(onewList)
        notifyDataSetChanged()
    }
}
