package ui.coconut.com.beelabs.app.coconutui.demo

import android.os.Bundle
import android.view.View
import app.beelabs.com.codebase.base.BaseActivity
import app.coconut.ui.com.beelabs.ui.component.UIAccordionContentComponent
import ui.coconut.com.beelabs.app.coconutui.R

class DemoAccordionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_accordion)

        val accordionButton = findViewById<View>(R.id.accordion_ciriciri)
        val accordionContent = findViewById<View>(R.id.ciri_ciri_layout)
        UIAccordionContentComponent.accordionView(accordionButton, accordionContent, false, null)
//        UiUtil.accordionView(
//                layout.findViewById<T>(R.id.review_polis_summary_button),
//                layout.findViewById<T>(R.id.accordion_polis_layout),
//                false, null)
    }
}