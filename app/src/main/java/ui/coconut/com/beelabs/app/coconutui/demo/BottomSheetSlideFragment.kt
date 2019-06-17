//package ui.coconut.com.beelabs.app.coconutui.demo
//
//import android.os.Bundle
//import android.support.design.widget.BottomSheetDialogFragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.content_slide.*
//import ui.coconut.com.beelabs.app.coconutui.R
//
//class BottomSheetSlideFragment : BottomSheetDialogFragment() {
//
//    private var fragmentView: View? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        fragmentView = inflater.inflate(R.layout.content_slide, container, false)
//        return fragmentView
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initView()
//    }
//
//    private fun initView() {
//        paymentButton.setOnClickListener {
//            this.dismiss()
//        }
//    }
//}