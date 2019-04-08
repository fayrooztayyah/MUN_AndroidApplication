package org.Nablus.eservices.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_about_us.*
import org.Nablus.eservices.R

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        txtAboutUs.text="في بداية النصف الثاني من القرن التاسع عشر وتحديدا في العام 1869، خرجت بلدية نابلس الى النور وعلى رأسها الشيخ محمد تفاحة الحسيني، أحد أعيان نابلس في ذلك الوقت، لتكون ضمن اولى البلديات الفلسطينية التي يتم تأسيسها في واحدة من اهم الحقب التاريخية التي مرت بها فلسطين والمنطقة عموما وهي الحقبة العثمانية. وقد اعتبر إنشاء بلدية نابلس آنذاك دليلا قويا على المكانة الرفيعة التي كانت تحتلها نابلس إبان فترة الخلافة العثمانية لفلسطين (1516-1917م)، ومدى الاهتمام الذي حظيت به من قبل السلطات العثمانية نظرا لكونها مركزا حضريا مرموقا ومنبعا للعلم والعلماء في مختلف حقول العلم والآداب والمعرفة. وقد ساهمت هذه المكانة الرفيعة التي احتلتها مدينة نابلس في تعزيز أهمية رئيس بلديتها الذي كان يحظى باحترام الجميع واعتبر دائما الشخصية الاولى على مستوى المدينة والمناطق المحيطة بها"

    }
}
