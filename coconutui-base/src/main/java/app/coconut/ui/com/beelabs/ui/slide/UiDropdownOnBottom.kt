package app.coconut.ui.com.beelabs.ui.slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import app.coconut.ui.com.beelabs.R


class UiDropdownOnBottom {

    class UiDropDown(var view: View)

    companion object {
        fun build(
            data: List<DropDownItemModel>,
            parentLayout: ViewGroup,
            slideLayoutResource: Int,
            slideContentID: Int,
            context: Context,
            itemListener: OnItemListener
        ): View? {

            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var panelSlide: View? = inflater.inflate(
                slideLayoutResource,
                parentLayout,
                false
            ) as View
            parentLayout.addView(panelSlide)
            val panelBg = panelSlide!!.findViewById<View>(R.id.slideBackground)
            val panelContent = panelSlide!!.findViewById<ViewGroup>(slideContentID)

            for((i, item) in data.withIndex()){
                var itemView = LayoutInflater.from(context).inflate(R.layout.item_dropdown, panelContent, false)
                itemView.findViewById<TextView>(R.id.tv_item).text = item.name
                itemView.findViewById<TextView>(R.id.tv_item).setOnClickListener { itemListener.onClick(itemView, i) }
                panelContent.addView(itemView)
            }

            // buttons panel
            val bottomUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
            panelContent.startAnimation(bottomUp)
            panelContent.visibility = View.VISIBLE
//            var dropDown = UiDropDown(panelSlide)
            return panelSlide
        }

        private fun onItemClickListener(itemView: View?, i: Int) {

        }

//    fun showTopPanel(context: Context, attachedRoot: ViewGroup, resourcePanelID: Int, onAttachedDataSlidePanel: app.clappingape.com.morinaga.ui.component.SlidePanel.OnAttachedDataSlidePanel?): View? {
//        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val panelSlide = inflater.inflate(resourcePanelID, attachedRoot, false) as ViewGroup // (View) root.findViewById(R.id.panel_background_slide);
//        if (onAttachedDataSlidePanel != null) {
//            onAttachedDataSlidePanel.onBindDataPanel(panelSlide)
//        } else {
//            attachedRoot.addView(panelSlide)
//        }
//        val panelContent = panelSlide.findViewById<ViewGroup>(R.id.panel_content)
//        // buttons panel
//        val topDown = AnimationUtils.loadAnimation(context, R.anim.slide_down)
//        panelContent.startAnimation(topDown)
//        panelContent.visibility = View.VISIBLE
//        return panelSlide
//    }
    }

    open class OnItemListener {
        open fun onClick(view: View, pos: Int){

        }
    }
}

class DropDownItemModel {

    var id: Int = 0
    var name: String? = ""

    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }
}
