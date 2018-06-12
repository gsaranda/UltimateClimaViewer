package mx.com.darklider.ultimateclimaviewer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import mx.com.darklider.climamodels.List



/**
 * Created by gabriel on 15/04/18.
 */
class ResultsViewAdapter(sourceList :ArrayList<List>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
/** Adaptador para recycker view, esta clase recibe un objeto con los datos del clima
 * y lo usa para desplegar los datos**/

    private var sourceList: ArrayList<List>
    var resultsViewItemListener:ResultsViewItemListener?=null

    fun setItemListener(resultsViewItemListener:ResultsViewItemListener){
        this.resultsViewItemListener=resultsViewItemListener
    }

    init {
        this.sourceList = sourceList

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resultHolder = holder as ResultViewHolder
        resultHolder.tvDate.setText(sourceList.get(position).Date)
        resultHolder.tvDescription.setText(sourceList.get(position).weather.get(0).description)
        resultHolder.tvTemperature.setText(sourceList.get(position).temperatureModel.temperature+"°C")
        resultHolder.llData.setOnClickListener(View.OnClickListener {resultsViewItemListener?.onItemCLicked(sourceList.get(position))  })
    }

    override fun getItemCount(): Int {
        return sourceList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.result_element, parent, false)
        return ResultViewHolder(view)

    }


    inner class ResultViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var tvDate: TextView
        var tvDescription: TextView
        var tvTemperature:TextView
        var llData:LinearLayout

        init {
            tvDate = v.findViewById(R.id.tv__date)
            tvDescription = v.findViewById(R.id.tv_description)
            tvTemperature=v.findViewById(R.id.tv_temperature)
            llData=v.findViewById(R.id.ll_data_container)
        }

    }

}