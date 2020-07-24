package ui.coconut.com.beelabs.app.coconutui.demo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import app.beelabs.com.codebase.base.BaseActivity
import app.coconut.ui.com.beelabs.ui.slide.DropDownItemModel
import app.coconut.ui.com.beelabs.ui.slide.UiDropdownOnBottom
import kotlinx.android.synthetic.main.activity_demo_drop_down.*
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

                dropDown = UiDropdownOnBottom.instance.build(
                    listOf(
                        DropDownItemModel("1", "satu"),
                        DropDownItemModel("2"),
                        DropDownItemModel("3"),
                        DropDownItemModel("4"),
                        DropDownItemModel("5"),
                        DropDownItemModel("6"),
                        DropDownItemModel("7"),
                        DropDownItemModel("8"),
                        DropDownItemModel("9"),
                        DropDownItemModel("10"),
                        DropDownItemModel("11"),
                        DropDownItemModel("12"),
                        DropDownItemModel("13"),
                        DropDownItemModel("14"),
                        DropDownItemModel("15"),
                        DropDownItemModel("16"),
                        DropDownItemModel("17"),
                        DropDownItemModel("18"),
                        DropDownItemModel("19"),
                        DropDownItemModel("20")
                    ),
                    parentLayout,
                    this,
                    object : UiDropdownOnBottom.OnItemListener() {
                        override fun onClick(view: View, pos: Int, item: DropDownItemModel) {
                            Toast.makeText(
                                this@DemoDropDownActivity,
                                "name: ${(view as TextView).text}|| ${item.value}",
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