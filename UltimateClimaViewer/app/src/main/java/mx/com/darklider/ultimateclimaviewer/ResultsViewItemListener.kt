package mx.com.darklider.ultimateclimaviewer

import mx.com.darklider.climamodels.List

interface ResultsViewItemListener {
    fun onItemCLicked(list: List)
}