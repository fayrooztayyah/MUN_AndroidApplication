package org.Nablus.eservices.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.smarteist.autoimageslider.*


import kotlinx.android.synthetic.main.activity_dc__informatiom.*
import com.smarteist.autoimageslider.SliderView




class DC_InformatiomActivity : AppCompatActivity() {


    lateinit var sliderView : SliderView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(org.Nablus.eservices.R.layout.activity_dc__informatiom)



        imageSlider.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setScrollTimeInSec(1); //set scroll delay in seconds :

        //setSliderViews();
        
    }

//
    private fun setSliderViews() {

        for (i in 0..3) {

            sliderView

           // val sliderView=SliderView(this)

            when (i) {
                0 -> sliderView.imageUrl =
                    "https://images.pexels.com/photos/547114/pexels-photo-547114.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                1 -> sliderView.imageUrl =
                    "https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                2 -> sliderView.imageUrl =
                    "https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"
                3 -> sliderView.imageUrl =
                    "https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            sliderView.description = "setDescription " + (i + 1)
            sliderView.setOnSliderClickListener {
                Toast.makeText(
                    this@DC_InformatiomActivity,
                    "This is slider " + (i + 1),
                    Toast.LENGTH_SHORT
                ).show()
            }

            //at last add this view in your layout :
            imageSlider.addSliderView(sliderView)
        }
    }
}
