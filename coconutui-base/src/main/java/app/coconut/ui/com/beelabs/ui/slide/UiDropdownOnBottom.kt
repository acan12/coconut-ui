package app.coconut.ui.com.beelabs.ui.slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import app.coconut.ui.com.beelabs.R


class UiDropdownOnBottom {
    var panelSlide: View? = null
    var parent: ViewGroup? = null

    companion object {
        fun newInstance(): UiDropdownOnBottom {
            return UiDropdownOnBottom()
        }
    }

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
        var panelSlide = inflater.inflate(
            slideLayoutResource,
            parentLayout,
            false
        ) as View
        this.panelSlide = panelSlide
        this.parent = parentLayout

        parentLayout.addView(panelSlide)
        val panelBg = panelSlide!!.findViewById<View>(R.id.slideBackground)
        val panelContent = panelSlide!!.findViewById<ViewGroup>(slideContentID)

        for ((i, item) in data.withIndex()) {
            var itemView =
                LayoutInflater.from(context).inflate(R.layout.item_dropdown, panelContent, false)
            itemView.findViewById<TextView>(R.id.tv_item).text = item.name
            itemView.findViewById<TextView>(R.id.tv_item)
                .setOnClickListener { itemListener.onClick(itemView, i) }
            panelContent.addView(itemView)
        }
        panelBg.setOnClickListener { itemListener.outItemClick() }
        // buttons panel
        val bottomUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        panelContent.startAnimation(bottomUp)
        panelContent.visibility = View.VISIBLE

        return panelSlide
    }

    fun dismiss(): Boolean {
        return try {
            if (panelSlide != null) {
                parent!!.removeView(panelSlide)
                this.panelSlide = null
                true
            } else false
        } catch (e: Exception) {
            false
        }

    }

    open class OnItemListener {
        open fun onClick(view: View, pos: Int) {

        }

        open fun outItemClick(){}
    }
}

class DropDownItemModel(var name: String)
