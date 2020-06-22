package ui.coconut.com.beelabs.app.coconutui.demo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import app.beelabs.com.codebase.base.BaseActivity
import app.coconut.ui.com.beelabs.ui.slide.DropDownItemModel
import app.coconut.ui.com.beelabs.ui.slide.UiDropdownOnBottom

import kotlinx.android.synthetic.main.activity_demo_drop_down.*
import kotlinx.android.synthetic.main.activity_demo_drop_down.view.*
import ui.coconut.com.beelabs.app.coconutui.R


class DemoDropDownActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

//    private var dropDown: UiDropdownOnBottom.UiDropDown? = null
    private var panelSlide: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_drop_down)

        // Custom Spinner Drop down elements
        dd_layout.setOnClickListener {
            if (panelSlide == null) {
                panelSlide = UiDropdownOnBottom.build(
                    listOf<DropDownItemModel>(
                        DropDownItemModel(1, "first"),
                        DropDownItemModel(2, "second")
                ),
                    parentLayout,
                    R.layout.slide_panel,
                    R.id.slideContent,
                    this,
                    object: UiDropdownOnBottom.OnItemListener(){
                        override fun onClick(view: View, pos: Int){
                            Toast.makeText(this@DemoDropDownActivity, "Pos: ${pos}", Toast.LENGTH_SHORT).show()
                        }

                    }
                )

//                panelSlide!!.setOnTouchListener(object: View.OnTouchListener{
//                    override fun onTouch(view: View?, p1: MotionEvent?): Boolean {
//                        Toast.makeText(this@DemoDropDownActivity, "Click", Toast.LENGTH_SHORT).show()
//
//                        when (view!!.id){
//                            R.id.option_male -> {
//                                Toast.makeText(this@DemoDropDownActivity, "male", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                        return true
//                    }
//
//                })
//                    if(it.id == R.id.option_male){
//                        Toast.makeText(this@DemoDropDownActivity, "male", Toast.LENGTH_SHORT).show()
//                    } else
//                    Toast.makeText(this@DemoDropDownActivity, "Click", Toast.LENGTH_SHORT).show()
//                }

//                panelSlide!!.let{
//                    findViewById<View>(R.id.panelBackground).setOnClickListener {
//                        onBackPressed()
//                    }
//                }
//
//                panelSlide!!.findViewById<View>(R.id.option_male).setOnClickListener {
//                    Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show()
//                    onBackPressed()
//                }
//
//                panelSlide!!.findViewById<View>(R.id.option_female).setOnClickListener {
//                    Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show()
//                    onBackPressed()
//                }
            }
        }



    // Spinner Drop down elements
        spinner.onItemSelectedListener = this
        val categories: MutableList<String> = ArrayList()
        categories.add("Item 1")
        categories.add("Item 2")
        categories.add("Item 3")
        categories.add("Item 4")
        categories.add("Item 5")
        categories.add("Item 6")

        // Creating adapter for spinner

        // Creating adapter for spinner
        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)

        // Drop down layout style - list view with radio button

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // attaching data adapter to spinner

        // attaching data adapter to spinner
        spinner.adapter = dataAdapter
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        // On selecting a spinner item
        // On selecting a spinner item
        val item: String = pos.toString()

        // Showing selected spinner item

        // Showing selected spinner item
        Toast.makeText(this, "Selected: $item", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        try {
            if(panelSlide != null){
                parentLayout.removeView(panelSlide)
                panelSlide = null
            }else super.onBackPressed()
        }catch (e: Exception){
            super.onBackPressed()
        }

    }
}