package org.Nablus.eservices.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_login.view.*
import org.Nablus.eservices.Activities.*
import org.Nablus.eservices.Models.PublicServicesSwitchBoard_Model
import org.Nablus.eservices.R
import java.util.ArrayList



class PublicServicesSwitchBoardListAdapter(val context: Context, var oList: List<PublicServicesSwitchBoard_Model>) :RecyclerView.Adapter<PublicServicesSwitchBoardListAdapter.PublicServicesListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicServicesListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_public_service_switch_board, parent, false)
        return PublicServicesListViewHolder(view)
    }
    override fun onBindViewHolder(holder: PublicServicesListViewHolder, position: Int) {
        holder?.setData(oList[position], position)
    }
    override fun getItemCount(): Int {
        return oList.size
    }
    inner class PublicServicesListViewHolder( itemView:  View) : RecyclerView.ViewHolder(itemView) {
        var Current_Row : PublicServicesSwitchBoard_Model? = null
        var Current_Position :  Int? = 0


        init {
            itemView.setOnClickListener {
                ////////////////Open Detail Intent///////////////////
                when(Current_Row!!.id){
                    1->{
                        var intent = Intent(context, PumpingScheduleActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }

                   0->{
                        var intent = Intent(context, ServicePumpingScheduleActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                   2->{
                        var intent = Intent(context, ServiceGuideActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                    3->{
                        var intent = Intent(context, WaterTankOrderActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                    7->{
                        var intent = Intent(context, ComplaintSwitchboardActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                        }
                    8->{
                        var intent = Intent(context, DataCenterSwitchboardActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                    4->{
                        var intent = Intent(context, LatestNewsActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                    5->{
                        var intent = Intent(context, NotificationActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }

                    6->{
                    var intent = Intent(context, SuggestionActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }

                }


            }
// itemView.imgFlag.setOnClickListener {
// var intent :  Intent = Intent()
// intent.action = Intent.ACTION_SEND
// intent.putExtra(Intent.EXTRA_TEXT, Current_Country!!.CountryName)
// intent.type = "text/plain"
// context.startActivity(Intent.createChooser(intent, "Share To:"))
//}
        }
        fun setData(dataModel : PublicServicesSwitchBoard_Model, pos: Int) {
// Set date to controls itemView.country_name.text = Country!!.CountryName
/* Assign Image to Image View// var resID :  Int
// ResId = context.getResources().getIdentifier(
// Country!!.Country_Flag,
// "drawable", "com.bishawi.mahmoud.countryapp"
 )
 itemView.imgFlag.setImageResource(resID)
*/
            itemView.textView.text=dataModel.text
            var ResId = context.getResources().getIdentifier(dataModel!!.drawable,
                    "drawable", "org.Nablus.eservices"
                        )
            itemView.imageView.setImageResource(ResId)
            //itemView.setBackgroundColor(Color.parseColor(Country_Topics.color))
           // itemView.imageView.setBackgroundColor(Color.parseColor(dataModel.color))
//            RelativeLayout.setBackgroundColor(Color.parseColor(item.color))
             itemView.findViewById<RelativeLayout>(R.id.relativeLayout).setBackgroundColor(Color.parseColor(dataModel.color))
            Current_Row = oList[pos]
            Current_Position = pos
        }
    }
    fun updateList(onewList:  List<PublicServicesSwitchBoard_Model>) {
        oList = ArrayList<PublicServicesSwitchBoard_Model>()
        (oList as ArrayList<PublicServicesSwitchBoard_Model>).addAll(onewList)
        notifyDataSetChanged()
    }
}













