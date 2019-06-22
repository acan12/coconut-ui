package ui.coconut.com.beelabs.app.coconutui.demo

import android.os.Bundle
import android.widget.Toast
import app.beelabs.com.codebase.base.BaseActivity
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import kotlinx.android.synthetic.main.activity_demo_calendar.*
import ui.coconut.com.beelabs.app.coconutui.R
import java.text.SimpleDateFormat
import java.util.Date

class DemoCalendarActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_calendar)

        calendarView.setDate(Date())
        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay?) {
                val c = eventDay!!.calendar
                val date = c.time
                val format = SimpleDateFormat("dd/MM/yyy")
                Toast.makeText(this@DemoCalendarActivity, format.format(date), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}