//package ui.coconut.com.beelabs.app.coconutui.demo
//
//import android.graphics.RectF
//import android.graphics.Typeface
//import android.os.Bundle
//import android.support.v4.content.ContextCompat
//import android.view.WindowManager
//import android.widget.SeekBar
//import android.widget.TextView
//import app.beelabs.com.codebase.base.BaseActivity
//import com.github.mikephil.charting.charts.BarChart
//import com.github.mikephil.charting.components.Legend
//import com.github.mikephil.charting.components.XAxis
//import com.github.mikephil.charting.components.YAxis
//import com.github.mikephil.charting.data.BarData
//import com.github.mikephil.charting.data.BarDataSet
//import com.github.mikephil.charting.data.BarEntry
//import com.github.mikephil.charting.data.Entry
//import com.github.mikephil.charting.highlight.Highlight
//import com.github.mikephil.charting.listener.OnChartValueSelectedListener
//import com.github.mikephil.charting.model.GradientColor
//import kotlinx.android.synthetic.main.activity_demo_chart.*
//
//
//class DemoChartActivity : BaseActivity(), SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {
//    override fun onValueSelected(e: Entry?, h: Highlight?) {
//    }
//
//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//
//    }
//
//    private var tfLight: Typeface? = null
//    private var chart: BarChart? = null
//    private var seekBarX: SeekBar? = null
//    private var seekBarY: SeekBar? = null
//    private var tvX: TextView? = null
//    private var tvY: TextView? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)
//
//        tfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
//        val chart = BarChart(this)
//        setContentView(chart)
//
//
////        drawChart()
//
//
//        title = "BarChartActivity"
//
//
//
////        seekBarX = findViewById(R.id.seekBar1)
////        seekBarY = findViewById<SeekBar>(R.id.seekBar2)
////
//        seekBarY!!.setOnSeekBarChangeListener(this)
//        seekBarX!!.setOnSeekBarChangeListener(this)
//
////        chart = findViewById(R.id.chart1)
//        chart1!!.setOnChartValueSelectedListener(this)
//
//        chart!!.setDrawBarShadow(false)
//        chart!!.setDrawValueAboveBar(true)
//
//        chart!!.description.isEnabled = false
//
////        // if more than 60 entries are displayed in the chart, no values will be
////        // drawn
//        chart!!.setMaxVisibleValueCount(60)
////
////        // scaling can now only be done on x- and y-axis separately
//        chart!!.setPinchZoom(false)
//
//        chart!!.setDrawGridBackground(false)
//        // chart.setDrawYLabels(false);
//
//        val xAxisFormatter = DayAxisValueFormatter(chart)
//
//        val xAxis = chart!!.xAxis
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.typeface = tfLight
//        xAxis.setDrawGridLines(false)
//        xAxis.granularity = 1f // only intervals of 1 day
//        xAxis.labelCount = 7
//        xAxis.valueFormatter = xAxisFormatter
//
//        val custom = MyValueFormatter("$")
//
//        val leftAxis = chart!!.axisLeft
//        leftAxis.typeface = tfLight
//        leftAxis.setLabelCount(8, false)
//        leftAxis.valueFormatter = custom
//        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
//        leftAxis.spaceTop = 15f
//        leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
//
//        val rightAxis = chart!!.axisRight
//        rightAxis.setDrawGridLines(false)
//        rightAxis.typeface = tfLight
//        rightAxis.setLabelCount(8, false)
//        rightAxis.valueFormatter = custom
//        rightAxis.spaceTop = 15f
//        rightAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
//
//        val l = chart!!.legend
//        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
//        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
//        l.orientation = Legend.LegendOrientation.HORIZONTAL
//        l.setDrawInside(false)
//        l.form = Legend.LegendForm.SQUARE
//        l.formSize = 9f
//        l.textSize = 11f
//        l.xEntrySpace = 4f
//
//        val mv = XYMarkerView(this, xAxisFormatter)
//        mv.setChartView(chart) // For bounds control
//        chart!!.marker = mv // Set the marker to the chart
//
//        // setting data
//        seekBarY!!.setProgress(50)
//        seekBarX!!.progress = 12
//
//        // chart.setDrawLegend(false);
//    }
//
////    private fun drawChart() {
////        val entries = ArrayList<BarEntry>()
////        entries.add(BarEntry(4f, 0f))
////        entries.add(BarEntry(8f, 1f))
////        entries.add(BarEntry(6f, 2f))
////        entries.add(BarEntry(12f, 3f))
////        entries.add(BarEntry(18f, 4f))
////        entries.add(BarEntry(9f, 5f))
//////        val dataset = BarDataSet(entries, "# of Calls")
////
////        val labels = ArrayList<String>()
////        labels.add("January")
////        labels.add("February")
////        labels.add("March")
////        labels.add("April")
////        labels.add("May")
////        labels.add("June")
////
////    }
//
//    private fun setData(count: Int, range: Float) {
//
//        val start = 1f
//
//        val values = ArrayList<BarEntry>()
//
//        var i = start.toInt()
//        while (i < start + count) {
//            val `val` = (Math.random() * (range + 1)).toFloat()
//
////            if (Math.random() * 100 < 25) {
////                values.add(BarEntry(i.toFloat(), `val`, resources.getDrawable(R.drawable.star)))
////            } else {
////                values.add(BarEntry(i.toFloat(), `val`))
////            }
//            i++
//        }
//
//        val set1: BarDataSet
//
//        if (chart!!.data != null && chart!!.data.dataSetCount > 0) {
//            set1 = chart!!.data.getDataSetByIndex(0) as BarDataSet
//            set1.values = values
//            chart!!.data.notifyDataChanged()
//            chart!!.notifyDataSetChanged()
//
//        } else {
//            set1 = BarDataSet(values, "The year 2017")
//
//            set1.setDrawIcons(false)
//
//            //            set1.setColors(ColorTemplate.MATERIAL_COLORS);
//
//            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
//            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
//            set1.setGradientColor(startColor, endColor);*/
//
//            val startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
//            val startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light)
//            val startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light)
//            val startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light)
//            val startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light)
//            val endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark)
//            val endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple)
//            val endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark)
//            val endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark)
//            val endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark)
//
//            val gradientColors = ArrayList<GradientColor>()
//            gradientColors.add(GradientColor(startColor1, endColor1))
//            gradientColors.add(GradientColor(startColor2, endColor2))
//            gradientColors.add(GradientColor(startColor3, endColor3))
//            gradientColors.add(GradientColor(startColor4, endColor4))
//            gradientColors.add(GradientColor(startColor5, endColor5))
//
//            set1.gradientColors = gradientColors
//
//            val dataSets = ArrayList<BarDataSet>()
//            dataSets.add(set1)
//
//            val data = BarData(dataSets)
//            data.setValueTextSize(10f)
//            data.setValueTypeface(tfLight)
//            data.setBarWidth(0.9f)
//
//            chart!!.data = data
//        }
//    }
//
////    fun onCreateOptionsMenu(menu: Menu): Boolean {
////        menuInflater.inflate(R.menu.bar, menu)
////        return true
////    }
//
////    fun onOptionsItemSelected(item: MenuItem): Boolean {
////
////        when (item.getItemId()) {
////            R.id.viewGithub -> {
////                val i = Intent(Intent.ACTION_VIEW)
////                i.data = Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/BarChartActivity.java")
////                startActivity(i)
////            }
////            R.id.actionToggleValues -> {
////                for (set in chart!!.data.dataSets)
////                    set.setDrawValues(!set.isDrawValuesEnabled)
////
////                chart!!.invalidate()
////            }
////            R.id.actionToggleIcons -> {
////                for (set in chart!!.data.dataSets)
////                    set.setDrawIcons(!set.isDrawIconsEnabled)
////
////                chart!!.invalidate()
////            }
////            R.id.actionToggleHighlight -> {
////                if (chart!!.data != null) {
////                    chart!!.data.isHighlightEnabled = !chart!!.data.isHighlightEnabled
////                    chart!!.invalidate()
////                }
////            }
////            R.id.actionTogglePinch -> {
////                if (chart!!.isPinchZoomEnabled)
////                    chart!!.setPinchZoom(false)
////                else
////                    chart!!.setPinchZoom(true)
////
////                chart!!.invalidate()
////            }
////            R.id.actionToggleAutoScaleMinMax -> {
////                chart!!.isAutoScaleMinMaxEnabled = !chart!!.isAutoScaleMinMaxEnabled
////                chart!!.notifyDataSetChanged()
////            }
////            R.id.actionToggleBarBorders -> {
////                for (set in chart!!.data.dataSets)
////                    (set as BarDataSet).barBorderWidth = if (set.getBarBorderWidth() == 1f) 0f else 1f
////
////                chart!!.invalidate()
////            }
////            R.id.animateX -> {
////                chart!!.animateX(2000)
////            }
////            R.id.animateY -> {
////                chart!!.animateY(2000)
////            }
////            R.id.animateXY -> {
////                chart!!.animateXY(2000, 2000)
////            }
////            R.id.actionSave -> {
////                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
////                    saveToGallery()
////                } else {
////                    requestStoragePermission(chart)
////                }
////            }
////        }
////        return true
////    }
//
////    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
////
////        tvX!!.text = (seekBarX!!.progress).toString()
////        tvY!!.setText(seekBarY!!.getProgress().toString())
////
////        setData(seekBarX!!.progress, seekBarY!!.getProgress().toFloat())
////        chart!!.invalidate()
////    }
//
////    protected fun saveToGallery() {
////        saveToGallery(chart, "BarChartActivity")
////    }
//
//    override fun onStartTrackingTouch(seekBar: SeekBar) {}
//
//    override fun onStopTrackingTouch(seekBar: SeekBar) {}
//
//    private val onValueSelectedRectF = RectF()
//
////    fun onValueSelected(e: Entry<*, *>?, h: Highlight) {
////
////        if (e == null)
////            return
////
////        val bounds = onValueSelectedRectF
////        chart!!.getBarBounds(e as BarEntry?, bounds)
////        val position = chart!!.getPosition(e, AxisDependency.LEFT)
////
////        Log.i("bounds", bounds.toString())
////        Log.i("position", position.toString())
////
////        Log.i("x-index",
////                "low: " + chart!!.lowestVisibleX + ", high: "
////                        + chart!!.highestVisibleX)
////
////        MPPointF.recycleInstance(position)
////    }
//
//    override fun onNothingSelected() {}
//
//}
