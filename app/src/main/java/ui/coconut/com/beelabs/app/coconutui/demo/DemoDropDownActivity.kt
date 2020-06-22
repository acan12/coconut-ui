package ui.coconut.com.beelabs.app.coconutui.demo

import android.os.Bundle
import android.view.View
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
                dropDown = UiDropdownOnBottom.newInstance()
                dropDown!!.build(
                    listOf<DropDownItemModel>(
                        DropDownItemModel("first"),
                        DropDownItemModel("second")
                    ),
                    parentLayout,
                    R.layout.slide_panel,
                    R.id.slideContent,
                    this,
                    object : UiDropdownOnBottom.OnItemListener() {
                        override fun onClick(view: View, pos: Int) {
                            Toast.makeText(
                                this@DemoDropDownActivity,
                                "Pos: ${pos}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun outItemClick() {
                            onBackPressed()
                        }

                    }
                )
            }
        }
    }

    override fun onBackPressed() {
        if (!dropDown!!.dismiss()) {
            super.onBackPressed()
        }
    }
}