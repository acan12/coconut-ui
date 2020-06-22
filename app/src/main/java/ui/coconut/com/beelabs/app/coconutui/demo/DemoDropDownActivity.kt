package ui.coconut.com.beelabs.app.coconutui.demo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import app.beelabs.com.codebase.base.BaseActivity
import app.coconut.ui.com.beelabs.ui.slide.DropDownItemModel
import app.coconut.ui.com.beelabs.ui.slide.UiDropdownOnBottom
import kotlinx.android.synthetic.main.activity_demo_drop_down.*
import kotlinx.android.synthetic.main.activity_demo_drop_down.view.*
import ui.coconut.com.beelabs.app.coconutui.R


class DemoDropDownActivity : BaseActivity() {

    private var dropDown: UiDropdownOnBottom? = null
    private var panelSlide: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_drop_down)

        // Custom Spinner Drop down elements
        dd_layout.setOnClickListener {
            if (panelSlide == null) {
                dropDown = UiDropdownOnBottom.newInstance().build(
                    listOf<DropDownItemModel>(
                        DropDownItemModel("first"),
                        DropDownItemModel("second")
                    ),
                    parentLayout,
                    this,
                    object : UiDropdownOnBottom.OnItemListener() {
                        override fun onClick(view: View, pos: Int) {
                            Toast.makeText(
                                this@DemoDropDownActivity,
                                "name: ${(view as TextView).text}",
                                Toast.LENGTH_SHORT
                            ).show()
                            onBackPressed()
                        }

                        override fun outsideItemClick() {
                            onBackPressed()
                        }

                    }
                )?.show()
            }
        }
    }

    override fun onBackPressed() {
        if (!dropDown!!.dismiss()) {
            super.onBackPressed()
        }
    }
}