package app.coconut.ui.com.beelabs.ui.slide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import app.coconut.ui.com.beelabs.R


class UiDropdownOnBottom {
    private var panelContent: ViewGroup? = null
    private var panelSlide: View? = null
    private var parent: ViewGroup? = null
    private var context: Context? = null

    private object HOLDER {
        val INSTANCE = UiDropdownOnBottom()
    }

    companion object {
        val instance: UiDropdownOnBottom by lazy { HOLDER.INSTANCE }
    }

    fun build(
        data: List<DropDownItemModel>,
        parentLayout: ViewGroup,
        context: Context,
        itemListener: OnItemListener
    ): UiDropdownOnBottom? {

        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var panelSlide = inflater.inflate(
            R.layout.coconut_slide_panel,
            parentLayout,
            false
        ) as View
        this.panelSlide = panelSlide
        this.parent = parentLayout
        this.context = context

        parentLayout.addView(panelSlide)
        val panelBg = panelSlide.findViewById<View>(R.id.slideBackgroundCoconut)
        panelContent = panelSlide.findViewById(R.id.slideContentCoconut)
        panelContent!!.removeAllViews()
        for ((i, item) in data.withIndex()) {
            var itemView =
                LayoutInflater.from(context).inflate(R.layout.item_dropdown, panelContent, false)
            var tvItem = itemView.findViewById<TextView>(R.id.tv_item_name)
            tvItem.text = item.name
            tvItem.setOnClickListener { itemListener.onClick(itemView, i) }
            panelContent!!.addView(itemView)
        }
        panelBg.setOnClickListener { itemListener.outsideItemClick() }
        return this
    }

    fun show(): UiDropdownOnBottom {
        // buttons panel
        val bottomUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)
        panelContent!!.startAnimation(bottomUp)
        panelContent!!.visibility = View.VISIBLE

        return this
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

        open fun outsideItemClick() {}
    }
}

class DropDownItemModel {
    var name: String? = null
    var value: Any? = null

    constructor(name: String)
    constructor(name: String, value: Any)
}
