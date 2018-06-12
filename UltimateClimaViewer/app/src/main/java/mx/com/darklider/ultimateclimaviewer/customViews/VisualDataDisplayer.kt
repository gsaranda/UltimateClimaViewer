package mx.com.darklider.ultimateclimaviewer.customViews

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import kotlinx.android.synthetic.main.visual_data_displayer.view.*
import mx.com.darklider.ultimateclimaviewer.R

class VisualDataDisplayer : ConstraintLayout {
    /*Vista que consiste en dos textviews para desplegar información en el formato
    * elemento: descripción*/

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.VisualDataDisplayer)
        init(attr)
    }

    private fun init(attr: TypedArray) {
        View.inflate(context, R.layout.visual_data_displayer, this)
        tv_data_description.setText(if (attr.hasValue(R.styleable.VisualDataDisplayer_description)) attr.getString(R.styleable.VisualDataDisplayer_description) else "")
        tv_data_to_display.setText(if (attr.hasValue(R.styleable.VisualDataDisplayer_data)) attr.getString(R.styleable.VisualDataDisplayer_data) else "")
    }

    fun setDescription(description:String){
        tv_data_description.setText(description)
    }
    //Se usa principalmente en el detalle del clima para desplegar los datos

    fun setData(data:String){
        tv_data_to_display.setText(data)
    }

}