package org.Nablus.eservices.Adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row_latest_news.view.*
import kotlinx.android.synthetic.main.row_notification.view.*
import org.Nablus.eservices.Activities.NewsDetailsActivity

import org.Nablus.eservices.Models.NotificationsModel
import org.Nablus.eservices.R

class NotificationAdapter(val context: Context, var oList: List<NotificationsModel>) :
    RecyclerView.Adapter<NotificationAdapter.TestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_notification, parent, false)
        return TestListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestListViewHolder, position: Int) {
        holder?.setData(oList[position], position)
    }

    override fun getItemCount(): Int {
        return oList.size
    }

    inner class TestListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var Current_LatestNews: NotificationsModel? = null
        var Current_Position: Int? = 0

        init {
            itemView.setOnClickListener {
                ////////////////Open Detail Intent///////////////////

               // var intent = Intent(context, NewsDetailsActivity::class.java)
             //   ContextCompat.startActivity(context, intent, null)

            }
// itemView.imgFlag.setOnClickListener {
// var intent :  Intent = Intent()
// intent.action = Intent.ACTION_SEND
// intent.putExtra(Intent.EXTRA_TEXT, Current_Test!!.CountryName)
// intent.type = "text/plain"
// context.startActivity(Intent.createChooser(intent, "Share To:"))
//}
        }

        fun setData(Test: NotificationsModel, pos: Int) {
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
            itemView.tvNotification_Title.text = Current_LatestNews!!.NotificationTitel
            itemView.tvNotification_Date.text=Current_LatestNews!!.NotificationDate


        }
    }

    fun updateList(onewList: List<NotificationsModel>) {
        oList = ArrayList<NotificationsModel>()
        (oList as ArrayList<NotificationsModel>).addAll(onewList)
        notifyDataSetChanged()
    }
}
