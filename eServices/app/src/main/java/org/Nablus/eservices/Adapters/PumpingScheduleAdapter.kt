package org.Nablus.eservices.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_pumping_schedule.view.*
import org.Nablus.eservices.Activities.NewsDetailsActivity

import org.Nablus.eservices.Models.PumbingModel
import org.Nablus.eservices.R



class PumpingScheduleAdapter(val context: Context, var oList: List<PumbingModel>) :RecyclerView.Adapter<PumpingScheduleAdapter.PumpingScheduleListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PumpingScheduleListViewHolder {


        val view = LayoutInflater.from(context).inflate(R.layout.row_pumping_schedule, parent, false)
        return PumpingScheduleListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PumpingScheduleListViewHolder, position: Int) {
        holder?.setData(oList[position], position)
    }

    override fun getItemCount(): Int {
        return oList.size
    }

    inner class PumpingScheduleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Current_PumpingSchedule: PumbingModel? = null
        var Current_Position: Int? = 0

        init {



            itemView.setOnClickListener {
                ////////////////Open Detail Intent///////////////////

                var intent = Intent(context, NewsDetailsActivity::class.java)
                ContextCompat.startActivity(context, intent, null)

            }
// itemView.imgFlag.setOnClickListener {
// var intent :  Intent = Intent()
// intent.action = Intent.ACTION_SEND
// intent.putExtra(Intent.EXTRA_TEXT, Current_Test!!.CountryName)
// intent.type = "text/plain"
// context.startActivity(Intent.createChooser(intent, "Share To:"))
//}
        }

        fun setData(Test: PumbingModel, pos: Int) {
// Set date to controls itemView.country_name.text = Country!!.CountryName
/* Assign Image to Image View// var resID :  Int
// ResId = context.getResources().getIdentifier(
// Country!!.Country_Flag,
// "drawable", "org.Nablus.eservices"
 )
 itemView.imgFlag.setImageResource(resID)
*/
            Current_PumpingSchedule = oList[pos]
            Current_Position = pos
            itemView.tvPubmping_Date.text = Current_PumpingSchedule!!.Pubmping_Date
            itemView.tvPubmping_Status.text = Current_PumpingSchedule!!.Pubmping_Status
            itemView.tvRegionName.text = Current_PumpingSchedule!!.Region_Name
           itemView.tvPubmping_EndDate.text = Current_PumpingSchedule!!.Pubmping_EndDate

            var statusSchedule = Current_PumpingSchedule!!.Pubmping_Status

            if (statusSchedule == "جاري الضخ"){

                itemView.statusImage.setImageDrawable(context.getResources().getDrawable(R.drawable.greenoval));

            }

            else if (statusSchedule == "بالانتظار"){

                itemView.statusImage.setImageDrawable(context.getResources().getDrawable(R.drawable.yellowoval));
            }

            else{
                itemView.statusImage.setImageDrawable(context.getResources().getDrawable(R.drawable.redoval));
            }


        }
    }

    fun updateList(onewList: List<PumbingModel>) {
        oList = ArrayList<PumbingModel>()
        (oList as ArrayList<PumbingModel>).addAll(onewList)
        notifyDataSetChanged()
    }

}