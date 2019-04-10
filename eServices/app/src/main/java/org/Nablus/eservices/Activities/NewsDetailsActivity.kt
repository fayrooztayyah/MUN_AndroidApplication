package org.Nablus.eservices.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_news_details.*

import org.Nablus.eservices.R
import android.R.attr.data
import android.content.Intent
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_latest_news.view.*


class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val News_title = getIntent().getStringExtra("News_title");
        val News_text = getIntent().getStringExtra("News_text");
        val News_image = getIntent().getStringExtra("News_image");
        val News_date = getIntent().getStringExtra("News_date");

        tvNewsTitle.text = News_title
        tvNewsText.text = News_text
        Picasso.get().load(News_image).resize(120  ,120).into(imNews_image)
        tvNewsDate.text = News_date

    }



}
