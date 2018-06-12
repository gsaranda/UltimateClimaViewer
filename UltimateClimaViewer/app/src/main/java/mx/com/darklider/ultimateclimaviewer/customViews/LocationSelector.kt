package mx.com.darklider.ultimateclimaviewer.customViews

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.location_selector.view.*
import mx.com.darklider.ultimateclimaviewer.R

class LocationSelector:CardView{
/*Vista personalizable para la selección de lugares, se uso un cardview por la animación que proporciona este elemento
al ser presionado*/

    constructor(context: Context):super(context) {

    }

    constructor(context: Context, attrs: AttributeSet):super(context, attrs) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.Selector)
        init(attr)

    }

    private fun init(attr: TypedArray) {
        View.inflate(context, R.layout.location_selector, this)
        tv_title.setText(if (attr.hasValue(R.styleable.Selector_btn_title)) attr.getString(R.styleable.Selector_btn_title) else "")
       val placeholderText = if (attr.hasValue(R.styleable.Selector_btn_subtitle)) attr.getString(R.styleable.Selector_btn_subtitle) else ""
        tv_description.setText(placeholderText)
        if (attr.hasValue(R.styleable.Selector_btn_image))
        iv_selector_icon.setImageDrawable( attr.getDrawable(R.styleable.Selector_btn_image))
    }

    fun setTitle(title: String) {
        tv_title.setText(title)
    }

    fun setDescription(description: String) {
        tv_description.setText(description)
    }


    override fun setOnClickListener(onClickListener: View.OnClickListener?) {
        cardview_locations.setOnClickListener(onClickListener)
    }

}