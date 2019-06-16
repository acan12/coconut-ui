package ui.coconut.com.beelabs.app.coconutui

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import app.coconut.ui.com.beelabs.ui.UIDateTimePicker
import app.coconut.ui.com.beelabs.ui.slide.UIBottomSheetSlidePanel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_slide.*
import kotlinx.android.synthetic.main.content_slide.view.*

class MainActivity : AppCompatActivity() {

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


