package ui.coconut.com.beelabs.app.coconutui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import app.beelabs.com.codebase.base.BaseActivity
import app.coconut.ui.com.beelabs.ui.component.UIDateTimePicker
import app.coconut.ui.com.beelabs.ui.slide.UIBottomSheetSlidePanel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_slide.*
import kotlinx.android.synthetic.main.content_slide.view.*
import ui.coconut.com.beelabs.app.coconutui.demo.DemoAccordionActivity
import ui.coconut.com.beelabs.app.coconutui.demo.DemoDropDownActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        demoButton.setOnClickListener { UIDateTimePicker.onPicker(demoButton, this@MainActivity) }

        demoBottomSheet.setOnClickListener {
            Toast.makeText(this@MainActivity, "Test Slide button", Toast.LENGTH_SHORT).show()
            showExtraInfo();
        }

        demoDialogBottomSheet.setOnClickListener {
            showSlideDialogPanel()
        }

        demoAccordion.setOnClickListener {
            intent = Intent(this@MainActivity, DemoAccordionActivity::class.java)
            startActivity(intent)
        }

        demoDropDown.setOnClickListener {
            intent = Intent(this@MainActivity, DemoDropDownActivity::class.java)
            startActivity(intent)
        }
    }


    private fun showSlideDialogPanel() {

        UIBottomSheetSlidePanel.showSlidePanel(R.layout.content_slide, this,
                object : UIBottomSheetSlidePanel.SlideCallbackListener() {
                    override fun onCall(dialog: BottomSheetDialog, view: View) {
                        view.paymentButton.setOnClickListener {
                            dialog.dismiss();
                        }
                    }
                })
    }


    private fun showExtraInfo() {
        val sheetBehavior = BottomSheetBehavior.from(bottom_sheet)
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {

                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {

                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {

                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {

                    }
                    BottomSheetBehavior.STATE_SETTLING -> {

                    }
                }
            }
        })

        sheetBehavior.peekHeight = 200
    }

}


